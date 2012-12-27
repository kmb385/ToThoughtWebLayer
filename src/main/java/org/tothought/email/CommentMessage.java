package org.tothought.email;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;
import org.tothought.email.abstracts.AbstractMailMessage;
import org.tothought.email.interfaces.MailMessage;

@Component
public class CommentMessage extends AbstractMailMessage implements MailMessage {

	
	@Override
	public Message getMessage() {

		Message message = new MimeMessage(super.getSession());
		try {
			message.setFrom(new InternetAddress("kmb385@gmail.com"));
			message.addRecipients(Message.RecipientType.TO, InternetAddress.parse("kmb385@gmail.com"));
			message.setSubject("A new comment has been made on the toThought Blog");
			message.setText("A new comment has been made on the toThought Blog");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return message;
	}

}
