package org.tothought.email.interfaces;

import javax.mail.Message;
import javax.mail.Session;

public interface MailMessage<T> {
	
	public Message getMessage(Session session);
	
	public void setBody(T entity);
}
