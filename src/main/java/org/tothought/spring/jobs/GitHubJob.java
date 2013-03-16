package org.tothought.spring.jobs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tothought.entities.Commit;
import org.tothought.entities.Skill;
import org.tothought.github.GitHubService;
import org.tothought.repositories.CommitRepository;
import org.tothought.repositories.SkillRepository;
import org.tothought.spring.jobs.abstracts.AbstractDataLoadJob;

@Service
@Transactional
public class GitHubJob extends AbstractDataLoadJob {

	private static final String JOB_NAME = "GITHUB";

	private Date recentCommitDate = null;

	Logger logger = LoggerFactory.getLogger(GitHubJob.class);

	@Value("${github.username}")
	private String username;

	@Value("${github.password}")
	private String password;

	@Autowired
	SkillRepository skillRepository;

	@Autowired
	CommitRepository repository;

	/**
	 * Job that pulls all commits from Github since the last data load and
	 * inserts them into the database.
	 * 
	 * Job is scheduled to run once per week.
	 */
	@Scheduled(fixedDelay = 604800000)
	public void perform() {
		try {
			super.logJobStarted(JOB_NAME);
			
			List<Commit> unsavedCommits = this.findUnsavedCommits();			
			this.insertCommits(unsavedCommits);
			
			Date dataCurrentDate = (this.recentCommitDate == null) ? this.getDataCurrentDate(JOB_NAME):this.recentCommitDate;
			super.logJobAndNotify(JOB_NAME, unsavedCommits.size(), dataCurrentDate);
						
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error performing GitHub data load.");
		}
	}

	/**
	 * Returns all commits from GitHub with a commit date > lastCommitSavedDate
	 * and with a tag.
	 * 
	 * Tags are stored in Commit messages within GitHub and take the form
	 * [TagName].
	 * 
	 * @return
	 */
	private List<Commit> findUnsavedCommits() {
		Date lastCommitSavedDate = super.getDataCurrentDate(JOB_NAME);

		GitHubService service = new GitHubService(this.username, this.password);

		// Commits to save
		List<Commit> unsavedCommits = new ArrayList<Commit>();

		for (Skill skill : this.skillRepository.findAll()) {
			if (skill.getTag() != null) {

				List<Commit> commits = service.getCommitsByTag(skill.getTag());
				logger.info("GitHub data load is processing " + commits.size() + " commits for skill: "
						+ skill.getTag().getName() + ".");
				
				if(!commits.isEmpty()){					
					for (Commit commit : commits) {
						Date commitDt = commit.getCommitDt();
						
						if (this.isUnsaved(lastCommitSavedDate, unsavedCommits, commit)) {
							unsavedCommits.add(commit);
							
							// Track the most recent commit date processed so it may
							// be saved in the log entry
							if (recentCommitDate == null || commitDt.after(recentCommitDate)) {
								recentCommitDate = commitDt;
							}
						}
					}
				}
			}
		}
		return unsavedCommits;
	}

	/**
	 * Flags whether a commit has already been saved or is in the process of
	 * being saved. This prevents duplicate commits, since commits may already
	 * be saved or have > 1 tag.
	 * 
	 * @param dataCurrentDate
	 * @param unsavedCommits
	 * @param commit
	 * @return
	 */
	private boolean isUnsaved(Date dataCurrentDate, List<Commit> unsavedCommits, Commit commit) {
		Date commitDt = commit.getCommitDt();
		return commitDt != null
				&& (dataCurrentDate == null || commitDt.after(dataCurrentDate)
						&& !unsavedCommits.contains(commit));
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
			repository.save(unsavedCommits);

		}
	}

	/**
	 * Debug method for local runs.
	 * 
	 * @param unsavedCommits
	 */
	@SuppressWarnings("unused")
	private void log(List<Commit> unsavedCommits) {
		int x = 0;
		for (Commit commit : unsavedCommits) {
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
