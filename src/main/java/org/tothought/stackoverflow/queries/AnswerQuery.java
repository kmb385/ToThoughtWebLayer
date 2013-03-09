package org.tothought.stackoverflow.queries;

public class AnswerQuery extends AbstractQuery {

	final static String FILTER = "!*0reXVBL(FFx3LJB01c";
	public AnswerQuery(String userId){
		super.buildBase(userId).setSite("stackoverflow").setMethod("answers")
		.addParameter("filter", FILTER);
	}
}
