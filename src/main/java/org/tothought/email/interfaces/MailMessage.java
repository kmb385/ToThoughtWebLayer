package org.tothought.email.interfaces;

import javax.mail.Message;

public interface MailMessage<T> {
	
	public Message getMessage();
	
	public void setBody(T entity);
}
