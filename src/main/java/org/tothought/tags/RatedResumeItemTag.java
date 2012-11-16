package org.tothought.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class RatedResumeItemTag extends SimpleTagSupport {

	String text;
	String imageSrc;
	String href;
	Integer rating;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImageSrc() {
		return imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	@Override
	public void doTag() throws JspException, IOException {
		PageContext pageContext = (PageContext) getJspContext();
		JspWriter out = pageContext.getOut();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div class=\"resume-block clearfix\">");
		sb.append("<div>").append("<img src=\"").append(this.imageSrc).append("\"/>").append("</div>");
		sb.append("<div class=\"name\">").append(this.text).append("</div>");
		sb.append("<div class=\"rating rating-").append(this.rating.toString()).append("\"/></div>");
		sb.append("</div>");
		
		out.write(sb.toString());
		super.doTag();
	}
	
	
}
