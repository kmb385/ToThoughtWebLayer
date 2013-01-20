package org.tothought.email;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;
import org.tothought.email.abstracts.AbstractMailMessage;
import org.tothought.email.interfaces.MailMessage;
import org.tothought.entities.Comment;

@Component(value="commentMessage")
public class CommentMessage extends AbstractMailMessage implements MailMessage<Comment> {

	private static final String MESSAGE_SUBJECT = "A new comment has been made on the toThought Blog";
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
	public void setBody(Comment comment) {
		body.append("\n").append(comment.getAuthor()).append(" commented: ").append("\n");
		body.append(comment.getBody());
	}

}
