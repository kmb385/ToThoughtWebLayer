package org.tothought.email;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;
import org.tothought.email.abstracts.AbstractMailMessage;
import org.tothought.email.interfaces.MailMessage;
import org.tothought.entities.DataLoadLogEntry;

@Component("dataLoadMessage")
public class DataLoadMessage extends AbstractMailMessage implements MailMessage<DataLoadLogEntry> {
	//TODO:  This can all be extracted into the super class
	
	private static final String MESSAGE_SUBJECT = "A data load has been ran for the toThought Blog.";
	StringBuilder body = new StringBuilder(MESSAGE_SUBJECT);

	@Override
	public Message getMessage() {
		Message message = new MimeMessage(super.getSession());
		try {
			message.setFrom(new InternetAddress("kmb385@gmail.com"));
			message.addRecipients(Message.RecipientType.TO, InternetAddress.parse("kmb385@gmail.com"));
			message.setSubject(MESSAGE_SUBJECT);
			message.setText(body.toString());
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public void setBody(DataLoadLogEntry logEntry) {
		DateFormat dateFormat = new SimpleDateFormat("MM/DD/yyyy HH:mm:ss");
		this.body.append("\n\n").append(logEntry.getLoadName()).append(" ran on ").append(dateFormat.format(logEntry.getRunDt()));
		this.body.append("\n\n").append(logEntry.getRecordsAdded()).append(" records were added.");
		
		if(logEntry.getDataCurrentDt() == null){			
			this.body.append("\n\n").append("Data is current as of ").append("today");			
		}else{
			this.body.append("\n\n").append("Data is current as of ").append(dateFormat.format(logEntry.getDataCurrentDt()));			
		}
	}



}
