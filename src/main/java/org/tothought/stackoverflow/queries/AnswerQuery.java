package org.tothought.stackoverflow.queries;

public class AnswerQuery extends Query {

	final static String FILTER = "!*Kc0z1PJKtK-g)_R";
	
	public AnswerQuery(String userId){
		super.buildBase(userId).setSite("stackoverflow").setMethod("answers")
		.addParameter("filter", FILTER);
	}
}
