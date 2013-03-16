package org.tothought.stackoverflow;

import java.net.URL;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tothought.entities.StackOverflowAnswer;
import org.tothought.entities.Tag;
import org.tothought.stackoverflow.queries.AbstractQuery;
import org.tothought.stackoverflow.queries.AnswerQuery;
import org.tothought.stackoverflow.result.AnswerResult;

public class StackOverflowService {

	private static final String USER_ID = "714969";

	private Logger logger = LoggerFactory.getLogger(getClass());

	public List<StackOverflowAnswer> answers = new LinkedList<StackOverflowAnswer>();

	/**
	 * Retrieve all answers on Stack Overflow
	 * 
	 * @return
	 */
	public List<StackOverflowAnswer> findAllAnswers() {
		return this.findAnswers(new AnswerQuery(USER_ID));
	}

	/**
	 * Returns all StackOverflow answers between the specified dates
	 * 
	 * @param toDate
	 * @param fromDate
	 * @return
	 */
	public List<StackOverflowAnswer> findAllAnswers(Date fromDate, Date toDate) {
		AnswerQuery query = new AnswerQuery(USER_ID);
		query.setFromDate(fromDate);
		query.setToDate(toDate);
		return this.findAnswers(query);
	}

	/**
	 * Returns all Stackoverflow answers
	 * 
	 * @param query
	 * @return
	 */
	private List<StackOverflowAnswer> findAnswers(AbstractQuery query) {

		if (logger.isInfoEnabled()) {
			logger.info("Retreiving answers from StackOverflow API.");
		}

		if (!this.answers.isEmpty()) {
			return this.answers;
		}

		int pageCount = 1;

		List<StackOverflowAnswer> answers = new LinkedList<StackOverflowAnswer>();

		AnswerResult result = null;

		while ((pageCount == 1 || result.isHasMore())
				&& (result == null || (result.getBackOff() == null || result.getBackOff() == 0))) {
			result = this.executeQuery(AnswerResult.class, query.setPage(pageCount++));
			answers.addAll(result.getAnswers());
		}

		return answers;

	}

	/**
	 * Find all answers with a specific tag.
	 * 
	 * @param tagName
	 * @return
	 */
	public List<StackOverflowAnswer> findAnswersByTag(String tagName) {
		List<StackOverflowAnswer> result = new LinkedList<StackOverflowAnswer>();
		List<StackOverflowAnswer> answers = this.findAllAnswers();

		for (StackOverflowAnswer answer : answers) {
			for (Tag tag : answer.getTags()) {
				if (tag.getName().equalsIgnoreCase(tagName)) {
					result.add(answer);
				}
			}
		}

		return result;
	}

	/**
	 * Execute the query and return results
	 * 
	 * @param clazz
	 * @param query
	 * @return
	 */
	private <T> T executeQuery(Class<T> clazz, AbstractQuery query) {

		if (logger.isInfoEnabled()) {
			logger.info("Executing StackOverflow API Query: " + query.getQuery());
		}

		ObjectMapper mapper = new ObjectMapper();
		URL url;
		T result = null;

		try {
			url = new URL(query.getQuery());
			result = mapper.readValue(new GZIPInputStream(url.openStream()), clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}
