package org.tothought.stackoverflow;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tothought.stackoverflow.entities.Answer;
import org.tothought.stackoverflow.queries.AbstractQuery;
import org.tothought.stackoverflow.queries.AnswerQuery;
import org.tothought.stackoverflow.result.AnswerResult;

public class StackOverflowService {

	private static final String USER_ID = "714969";

	private Logger logger = LoggerFactory.getLogger(getClass());
	public List<Answer> answers = new LinkedList<Answer>();
		
	/**
	 * Retrieve all answers on Stack Overflow
	 * @return
	 */
	public List<Answer> findAllAnswers(){
		return this.findAllAnswers(new AnswerQuery(USER_ID));
	}
	
	public List<Answer> findAllAnswers(Date toDate, Date fromDate){
		AnswerQuery query = new AnswerQuery(USER_ID);
		query.setFromDate(fromDate);
		query.setToDate(toDate);
		return this.findAllAnswers(query);
	}
	
	private List<Answer> findAllAnswers(AbstractQuery query){
		
		if(logger.isInfoEnabled()){			
			logger.info("Retreiving answers from StackOverflow API.");
		}
		
		if(!this.answers.isEmpty()){
			return this.answers;
		}
		
		int pageCount = 1;

		List<Answer> answers = new LinkedList<Answer>();
				
		AnswerResult result = null; 
		while((pageCount == 1 || result.isHasMore()) && (result == null || (result.getBackOff() == null  || !result.getBackOff()))){
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
	public List<Answer> findAnswersByTag(String tagName){
		List<Answer> result = new LinkedList<Answer>();
		List<Answer> answers = this.findAllAnswers();
		
		for(Answer answer: answers){
			for(String tag:answer.getTags()){
				if(tag.equalsIgnoreCase(tagName)){
					result.add(answer);					
				}
			}
		}
		
		return result;
	}
	
	/**
	 * Execute the query and return results
	 * @param clazz
	 * @param query
	 * @return
	 */
	private <T> T executeQuery(Class<T> clazz, AbstractQuery query) {		
		
		if(logger.isInfoEnabled()){			
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
	
	public static void main(String[] args) throws IOException {
		StackOverflowService service = new StackOverflowService();
//		List<Answer> answers = service.findAllAnswers();
//		List<Answer> answers = service.findAnswersByTag("Java");
		Date toDate = new Date(2013,4,1);
		Date fromDate = new Date(2013,4,2);
		List<Answer> answers = service.findAllAnswers(toDate, fromDate);
		for(Answer answer:answers){
			System.out.println(answer.toString());
		}
	}
}
