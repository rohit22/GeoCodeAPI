package com.api.utils;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmail {
	public static Session getSession(final String username, final String password){
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username,password);
				}
			});
		return session;
	}
	public static boolean sendEmail(final Session session, final String messageContent, final String toId, final String subject){
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("app4api.noreply@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(toId));
			message.setSubject(subject);
			message.setText(messageContent);
			
			message.setReplyTo(null);
			Transport.send(message);

			System.out.println("Done");
			return true;

		} catch (MessagingException e) {
			System.out.println(new RuntimeException(e));
			return false;
		}
	}
	
	public static boolean sendEmailWithAttachment(final Session session, final String messageContent, final String toId, final String subject, final String file, final String attachmentName){
		try {
			   Message message = new MimeMessage(session);
			   message.setFrom(new InternetAddress("app4api.noreply@gmail.com"));
		        message.setRecipients(Message.RecipientType.TO,
		                InternetAddress.parse(toId));
		        message.setSubject(subject);
		        message.setText(messageContent);

		        MimeBodyPart messageBodyPart = new MimeBodyPart();

		        Multipart multipart = new MimeMultipart();

		        messageBodyPart = new MimeBodyPart();
//		        String file = "path of file to be attached";
//		        String fileName = "subject";
		        DataSource source = new FileDataSource(file);
		        messageBodyPart.setDataHandler(new DataHandler(source));
		        messageBodyPart.setFileName(attachmentName);
		        multipart.addBodyPart(messageBodyPart);

		        message.setContent(multipart);

		        System.out.println("Sending");

		        Transport.send(message);

		        System.out.println("Done");
		        return true;
		} catch (MessagingException e) {
			System.out.println(new RuntimeException(e));
			return false;
		}
	}
	
	
	public static void main(String[] args) {
		
		Session s = getSession("app4api.noreply","digitalCentersIntern");
	//	System.out.println(sendEmail(s,"Hi","rohit14331@gmail.com","Test"));
		System.out.println(sendEmailWithAttachment(s, "Hi", "rohit14331@gmail.com", "Test","pom.xml","pom.xml"));
	}
}
