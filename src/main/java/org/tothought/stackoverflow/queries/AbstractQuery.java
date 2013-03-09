package org.tothought.stackoverflow.queries;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractQuery{

	protected static final String STACK_API_BASE_DOMAIN = "http://api.stackexchange.com";

	protected static final String USERS = "/users/";

	protected static final String BASE_QUERY = STACK_API_BASE_DOMAIN + USERS;

	protected static final String TO_DATE_PARAMETER = "todate";
	
	protected static final String FROM_DATE_PARAMETER = "fromdate";

	private StringBuilder query = new StringBuilder();
		
	private Map<String,String> params = new HashMap<String,String>();
	
	protected AbstractQuery buildBase(String userId) {
		if(query.length() == 0){
			query = new StringBuilder(BASE_QUERY + userId);
			return this;			
		}
		
		throw new RuntimeException("Query base has already been built.");
	}

	protected AbstractQuery setSite(String site){
		params.put("site", site);
		return this;
	}
	
	protected AbstractQuery setMethod(String methodName) {
		query.append("/").append(methodName);
		return this;
	}
	
	public AbstractQuery setPage(int page){
		this.params.put("page", String.valueOf(page));
		return this;
	}
	
	public AbstractQuery addParameter(String key, String value){
		this.params.put(key, value);
		return this;
	}
	
	public String getQuery(){
		
		if(params.size() == 0){
			return query.toString();
		}
		
		StringBuilder sb = new StringBuilder(this.query.toString());
		sb.append("?");

		for(Map.Entry<String,String> param: params.entrySet()){
			sb.append(param.getKey()).append("=").append(param.getValue());
			sb.append("&");
		}
		
		return sb.substring(0, sb.lastIndexOf("&"));
	}
	
	public AbstractQuery setToDate(Date date){
		String dateAsLong = Long.toString(date.getTime());
		//this.addParameter(TO_DATE_PARAMETER, dateAsLong);
		this.addParameter(TO_DATE_PARAMETER,  "1362182400");
		return this;
	}
	
	public AbstractQuery setFromDate(Date date){
		String dateAsLong = Long.toString(date.getTime());
		//this.addParameter(FROM_DATE_PARAMETER, dateAsLong);
		this.addParameter(FROM_DATE_PARAMETER, "1362096000");
		return this;
	}
	
}
