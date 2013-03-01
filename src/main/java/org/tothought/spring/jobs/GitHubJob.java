package org.tothought.spring.jobs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tothought.email.DataLoadMessage;
import org.tothought.email.EmailService;
import org.tothought.entities.Commit;
import org.tothought.entities.DataLoadLogEntry;
import org.tothought.entities.Skill;
import org.tothought.github.GitHubService;
import org.tothought.repositories.CommitRepository;
import org.tothought.repositories.DataLoadLogEntryRepository;
import org.tothought.repositories.RecentDataLoadLogEntryRepository;
import org.tothought.repositories.SkillRepository;
import org.tothought.views.RecentDataLoadLogEntry;

@Service
@Transactional
public class GitHubJob {

	private static final String JOB_NAME = "GITHUB";

	private Date recentCommitDate = null;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	
	Logger logger = LoggerFactory.getLogger(GitHubJob.class);

	@Value("${github.username}")
	private String username;
	
	@Value("${github.password}")
	private String password;
	
	@Autowired
	EmailService emailService;

	@Autowired
	DataLoadLogEntryRepository dataLoadLogEntryRepository;

	@Autowired
	SkillRepository skillRepository;

	@Autowired
	RecentDataLoadLogEntryRepository recentDataLoadLogEntryRepository;

	@Autowired
	CommitRepository repository;


	/**
	 * Job that pulls all commits from Github since the last data load and inserts them
	 * into the database.
	 * 
	 * Job is scheduled to run once per week.
	 */
	@Scheduled(fixedDelay = 604800000)
	public void perform() {
		try {
			logger.info("Starting GitHub Data Load..");
			
			List<Commit> unsavedCommits = this.findUnsavedCommits();
			
			this.insertCommits(unsavedCommits);
			this.logJob(unsavedCommits);
			
			logger.info("GitHub data load has completed.");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error performing GitHub data load.");
		}
	}

	/**
	 * Retrieves the date the last saved commit was commited to GITHUB.  This value is stored
	 * in the view V_DATA_LOAD_LOG_ENTRY.
	 * 
	 * @return
	 */
	private Date getLastCommitSavedDate() {
		Date lastCommitSavedDate = null;
		RecentDataLoadLogEntry logEntry = recentDataLoadLogEntryRepository.findByLoadName(JOB_NAME);
		
		if (logEntry != null) {
			Calendar calendar = Calendar.getInstance();

			//Add 1 day to date to prevent processing the same records, depending on run/commit time records could be lost.
			calendar.setTime(logEntry.getDataCurrentDt());
			calendar.add(Calendar.DATE, 1);
			lastCommitSavedDate = calendar.getTime();
		}
		
		logger.info((lastCommitSavedDate != null) ? "GitHub data load is processing commits made after "
				+ dateFormat.format(lastCommitSavedDate) : "GitHub data load is processing all commits");
		
		return lastCommitSavedDate;
	}
	
	/**
	 * Returns all commits from GitHub with a commit date > lastCommitSavedDate and with a tag.
	 * 
	 * Tags are stored in Commit messages within GitHub and take the form [TagName].
	 * 
	 * @return
	 */
	private List<Commit> findUnsavedCommits() {
		Date lastCommitSavedDate = this.getLastCommitSavedDate();

		GitHubService service = new GitHubService(this.username, this.password);

		// Commits to save
		List<Commit> unsavedCommits = new ArrayList<Commit>();

		for (Skill skill : this.skillRepository.findAll()) {
			if(skill.getTag() != null){
				
				List<Commit> commits = service.getCommitsByTag(skill.getTag());					
				logger.info("GitHub data load is processing " + commits.size() + " commits for skill: " + skill.getTag().getName() + ".");
				
				for (Commit commit : commits) {
					Date commitDt = commit.getCommitDt();
					
					if (this.isUnsaved(lastCommitSavedDate, unsavedCommits, commit)) {
						unsavedCommits.add(commit);
						
						//Track the most recent commit date processed so it may be saved in the log entry
						if (recentCommitDate == null || commitDt.after(recentCommitDate)) {
							recentCommitDate = commitDt;
						}
					}
				}	
			}
		}
		return unsavedCommits;
	}

	/**
	 * Flags whether a commit has already been saved or is in the process of being saved.  This
	 * prevents duplicate commits, since commits may already be saved or have > 1 tag.
	 * 
	 * @param dataCurrentDate
	 * @param unsavedCommits
	 * @param commit
	 * @return
	 */
	private boolean isUnsaved(Date dataCurrentDate, List<Commit> unsavedCommits, Commit commit) {
		Date commitDt = commit.getCommitDt();
		return commitDt != null && (dataCurrentDate == null || commitDt.after(dataCurrentDate) && !unsavedCommits.contains(commit));
	}
	
	/**
	 * Inserts the fresh commits into the database.
	 * 
	 * @param unsavedCommits
	 */
	private void insertCommits(List<Commit> unsavedCommits) {
		int unsavedCommitsCount = unsavedCommits.size();
		
		if (unsavedCommitsCount > 0) {
			logger.info("GitHub data load is saving " + unsavedCommitsCount + " commits.");
			//this.log(unsavedCommits);
			
			repository.save(unsavedCommits);	
		}
	}
	
	/**
	 * Saves the job run into the data log entry and notifies administrators.
	 * @param unsavedCommits
	 */
	private void logJob(List<Commit> unsavedCommits) {
		DataLoadMessage dataLoadMessage = new DataLoadMessage();
		DataLoadLogEntry dataLoadLogEntry = new DataLoadLogEntry(JOB_NAME, unsavedCommits.size(), this.recentCommitDate);
		dataLoadLogEntryRepository.save(dataLoadLogEntry);
		dataLoadMessage.setBody(dataLoadLogEntry);
		emailService.sendMessage(dataLoadMessage);
	}
	
	/**
	 * Debug method for local runs.
	 * 
	 * @param unsavedCommits
	 */
	@SuppressWarnings("unused")
	private void log(List<Commit> unsavedCommits) {
		int x = 0;
		for(Commit commit: unsavedCommits){
			logger.info("--- Commit ---" + ++x);
			logger.info(commit.getApiUrl());
			logger.info(commit.getHtmlUrl());
			logger.info(commit.getMessage());
			logger.info(commit.getSha());
			logger.info(commit.getTitle());
			logger.info(commit.getCommitDt().toString());
			logger.info(String.valueOf(commit.getTags().size()));
			logger.info("-- End Commit --");
		}
		
	}
}
