package org.tothought.stackoverflow.queries;

import java.util.HashMap;
import java.util.Map;

public abstract class Query{

	protected static final String STACK_API_BASE_DOMAIN = "http://api.stackexchange.com";

	protected static final String USERS = "/users/";

	protected static final String BASE_QUERY = STACK_API_BASE_DOMAIN + USERS;
	
	private StringBuilder query = new StringBuilder();
		
	private Map<String,String> params = new HashMap<String,String>();
	
	protected Query buildBase(String userId) {
		if(query.length() == 0){
			query = new StringBuilder(BASE_QUERY + userId);
			return this;			
		}
		
		throw new RuntimeException("Query base has already been built.");
	}

	protected Query setSite(String site){
		params.put("site", site);
		return this;
	}
	
	protected Query setMethod(String methodName) {
		query.append("/").append(methodName);
		return this;
	}
	
	public Query setPage(int page){
		this.params.put("page", String.valueOf(page));
		return this;
	}
	
	public Query addParameter(String key, String value){
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
	
}
