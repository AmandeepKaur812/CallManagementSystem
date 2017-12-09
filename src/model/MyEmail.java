package model;

import java.io.*;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;


public class MyEmail 
{
	public static void sendEmail(String toAddress,String subject, String message) throws AddressException,MessagingException 
	{
		String host = "smtp.gmail.com";
		String port = "587";
		final String userName="ak7225197@gmail.com";
		String password = "amankaur123456789";
		
		// sets SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port); 
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		// creates a new session with an authenticator
//	Authenticator auth = new Authenticator() 
//		{
//			public PasswordAuthentication getPasswordAuthentication() 
//			{
//				return new PasswordAuthentication(userName,"cmscmscms");
//			}
//		};

	//	Session session = Session.getInstance(properties, auth);
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication(userName, password);
		    }
		});
		MimeMessage msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(userName));
		InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSubject(subject);
		msg.setSentDate(new Date());
		msg.setContent(message,"text/html");
		Transport.send(msg);

	}


}
