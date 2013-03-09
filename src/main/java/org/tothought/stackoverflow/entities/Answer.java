package org.tothought.stackoverflow.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Answer {

	private Integer answerId;
	private Date createDt;
	private String title;
	private List<String> tags = new ArrayList<String>();
	private String link;
	
	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	@JsonProperty("answer_id")
	public Integer getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Integer answerId) {
		this.answerId = answerId;
	}

	@JsonProperty("creation_date")
	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("-----------  Answer ---------").append("\n");
		sb.append("Answer Id: ").append(this.answerId).append("\n");
		sb.append("Link: ").append(this.link).append("\n");
		sb.append("Title: ").append(this.title).append("\n");
		sb.append("Tags").append("\n");
		for(String tag: this.tags){
			sb.append(tag).append("\n");
		}
		return sb.toString();
	}

	
}
