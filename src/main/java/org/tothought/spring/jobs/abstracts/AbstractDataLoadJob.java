package org.tothought.spring.jobs.abstracts;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tothought.email.DataLoadMessage;
import org.tothought.email.EmailService;
import org.tothought.entities.DataLoadLogEntry;
import org.tothought.repositories.DataLoadLogEntryRepository;
import org.tothought.repositories.RecentDataLoadLogEntryRepository;
import org.tothought.views.RecentDataLoadLogEntry;

@Service
@Transactional
public abstract class AbstractDataLoadJob {

	private Logger logger = LoggerFactory.getLogger(AbstractDataLoadJob.class);

	public SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

	@Autowired
	protected RecentDataLoadLogEntryRepository recentDataLoadLogEntryRepository;

	@Autowired
	DataLoadLogEntryRepository dataLoadLogEntryRepository;
	
	
	@Autowired
	EmailService emailService;

	/**
	 * Retrieves the date the of the last loaded piece of data.  This value is stored
	 * in the view V_DATA_LOAD_LOG_ENTRY.
	 * 
	 * @return
	 */
	protected Date getDataCurrentDate(String JOB_NAME) {
		Date dataCurrentDate = null;
		RecentDataLoadLogEntry logEntry = recentDataLoadLogEntryRepository.findByLoadName(JOB_NAME);

		if (logEntry != null) {
			Calendar calendar = Calendar.getInstance();

			// Add 1 day to date to prevent processing the same records,
			// depending on run/commit time records could be lost.
			calendar.setTime(logEntry.getDataCurrentDt());
			calendar.add(Calendar.DATE, 1);
			dataCurrentDate = calendar.getTime();
		}

		this.logDataCurrentDate(JOB_NAME, dataCurrentDate);

		return dataCurrentDate;
	}
	
	/**
	 * Saves the job run into the data log entry.  Records the date of the most recent record and the
	 * number of records inserted.  Sends email notifying admin of the job run.
	 * 
	 * @param unsavedCommits
	 */
	protected void logJobAndNotify(String jobName, int recordsModifiedCount, Date dataCurrentDate) {
		DataLoadMessage dataLoadMessage = new DataLoadMessage();
		DataLoadLogEntry dataLoadLogEntry = new DataLoadLogEntry(jobName, recordsModifiedCount, dataCurrentDate);

		dataLoadLogEntryRepository.save(dataLoadLogEntry);

		dataLoadMessage.setBody(dataLoadLogEntry);
		emailService.sendMessage(dataLoadMessage);
		this.logJobCompletion(jobName);
	}


	/**
	 * Logs the data current date for the current execution of a job.
	 * @param jobName
	 * @param dataCurrentDate
	 */
	protected void logRetreivalDate(String jobName, Date dataCurrentDate){
		logger.info(jobName + " is retreiving data for records after " + dataCurrentDate + ".");
	}
	
	/**
	 * Logs the start of a job
	 * @param jobName
	 */
	protected void logJobStarted(String jobName){
		logger.info(jobName + " has started at " + new Date().toString() + ".");
	}
	
	/**
	 * Logs the end of a job.
	 * @param jobName
	 */
	protected void logJobCompletion(String jobName){
		logger.info(jobName + " has finished at " + new Date().toString() + ".");
	}
	
	
	private void logDataCurrentDate(String JOB_NAME, Date dataCurrentDate) {
		logger.info((dataCurrentDate != null) ? "Data load " + JOB_NAME
				+ ": is processing commits made after " + dateFormat.format(dataCurrentDate)
				: "Data load " + JOB_NAME + ": is processing all commits");
	}

}
