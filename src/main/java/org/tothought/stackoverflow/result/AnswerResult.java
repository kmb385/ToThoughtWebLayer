package org.tothought.stackoverflow.result;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.tothought.stackoverflow.entities.Answer;

public class AnswerResult extends AbstractResult {

	@JsonProperty("items")
	List<Answer> answers = new ArrayList<Answer>();

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
}
