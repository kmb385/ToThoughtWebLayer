package org.tothought.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ControlTag extends SimpleTagSupport {

	private String text = "";
	private String classes = "";
	private String href = "#";

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public void doTag() throws JspException {
		PageContext pageContext = (PageContext) getJspContext();
		JspWriter out = pageContext.getOut();

		try {
			StringBuilder sb = new StringBuilder();

			sb.append("<a href=\"" + this.href + "\" class=\"control\">");
			sb.append("<span class=\"img " + this.classes + "\"></span>");
			sb.append("<span class=\"text\">" + this.text + "</span>");
			sb.append("</a>");

			out.write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
