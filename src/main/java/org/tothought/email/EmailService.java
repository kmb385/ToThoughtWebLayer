package org.tothought.email;

import javax.mail.MessagingException;
import javax.mail.Transport;

import org.springframework.stereotype.Component;
import org.tothought.email.interfaces.MailMessage;


@Component
public class EmailService {

	public <T> void sendMessage(MailMessage<T> message){
		try {
			Transport.send(message.getMessage());
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
