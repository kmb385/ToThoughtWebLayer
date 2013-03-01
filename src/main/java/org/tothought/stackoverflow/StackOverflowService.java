package org.tothought.stackoverflow;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tothought.stackoverflow.entities.Answer;
import org.tothought.stackoverflow.queries.AnswerQuery;
import org.tothought.stackoverflow.queries.Query;
import org.tothought.stackoverflow.result.AnswerResult;

public class StackOverflowService {

	private static final String USER_ID = "714969";

	private Logger logger = LoggerFactory.getLogger(getClass());
	public List<Answer> answers = new LinkedList<Answer>();
		
	/**
	 * Retreive all answers on Stack Overflow
	 * @return
	 */
	public List<Answer> findAllAnswers(){
		
		if(!this.answers.isEmpty()){
			return this.answers;
		}
		
		int pageCount = 1;

		List<Answer> answers = new LinkedList<Answer>();
		StackOverflowService service = new StackOverflowService();
		AnswerQuery query = new AnswerQuery(USER_ID);
		
		if(logger.isDebugEnabled()){			
			logger.debug("Executing StackOverflow API Query: " + query.getQuery());
		}
		
		AnswerResult result = null; 
		while(pageCount == 1 || result.isHasMore()){
			result = service.executeQuery(AnswerResult.class, query.setPage(pageCount++));
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
	private <T> T executeQuery(Class<T> clazz, Query query) {
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
		List<Answer> answers = service.findAllAnswers();
		for(Answer answer:answers){
			System.out.println(answer.getTitle());
		}
		
//		int pageCount = 1;
//		StackOverflowService service = new StackOverflowService();
//		AnswerQuery query = new AnswerQuery(USER_ID);
//		System.out.println(query.getQuery());
//		AnswerResult result = null; 
//		while(pageCount == 1 || result.isHasMore()){
//			result = service.executeQuery(AnswerResult.class, query.setPage(pageCount++));
//			for(Answer answer: result.getAnswers()){
//				System.out.println(answer.getTitle());
//			}
//		}	
				
//		System.out.println(result.getAnswers().get(0).getLink());
//		URL url;

		// try {
		// // url = new URL(STACK_API_BASE_DOMAIN + "/users/714969/answers" +
		// SITE_PARAMETER + "&filter=!6N0G7E63eenl8");
		// // url = new URL(STACK_API_BASE_DOMAIN + "/users/714969/answers" +
		// SITE_PARAMETER + "&filter=!*Kc0z1PJKtK-g)_R");
		// //
		// // Query query = new Query();
		// //
		// query.buildBase(USER_ID).setMethod("answers").setSite("stackoverflow");
		// // System.out.println(query.getQuery());
		// // System.out.println(url.toString());
		// //
		// // BufferedReader in = new BufferedReader(new InputStreamReader(new
		// GZIPInputStream(url.openStream())));
		// // ObjectMapper mapper = new ObjectMapper();
		// // AnswerResult wrapper = mapper.readValue(new
		// GZIPInputStream(url.openStream()), AnswerResult.class);
		// // System.out.println(wrapper.getQuotaMax());
		// /*
		// String inputLine;
		// while((inputLine = in.readLine()) != null){
		//
		// System.out.println(inputLine);
		// }*/
		//
		// // in.close();
		// } catch (MalformedURLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}
}
