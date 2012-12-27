package org.tothought.email.abstracts;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.tothought.email.EmailConfiguration;

public class AbstractMailMessage {

	@Autowired
	private EmailConfiguration configuration;

	public EmailConfiguration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(EmailConfiguration configuration) {
		this.configuration = configuration;
	}
	
	public Session getSession(){
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", configuration.getHost());
		props.put("mail.smtp.port", configuration.getPort());
		
		Session session = Session.getInstance(props, new Authenticator(){

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(configuration.getUsername(), configuration.getPassword());
			}
		});
		
		return session;
	}
	
}
