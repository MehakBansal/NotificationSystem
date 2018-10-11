package com.notify.controllers;



import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.notify.domain.MessageRequest;
import com.notify.domain.PacketForTransport;

public class MessageForMailConfiguration {
	
	final static String username = "dummymailsender.testing@gmail.com";
	final static String password = "231292231292";

	public  static PacketForTransport generateConfiguredMessageForSendingMail(MessageRequest msgrecieved) throws AddressException, MessagingException {
		
		PacketForTransport packet=new PacketForTransport();

		Properties props = new Properties();
		props.put("mail.smtp.user", "dummymailsender.testing@gmail.com");
		props.put("mail.smtp.starttls.enable","true");
		props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.socketFactory.fallback", "false");
		
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(msgrecieved.getSender()));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(msgrecieved.getTo()));
			message.setSubject(msgrecieved.getSubject());
			message.setText(msgrecieved.getMessage());
			
			packet.setMessage(message);
			packet.setSession(session);
			return packet;
		
	}
	
	public static void generateConfiguredTransportForMail(PacketForTransport packet) throws MessagingException {
		
		Transport transport = packet.getSession().getTransport("smtp");
		transport.connect("smtp.gmail.com", 465, "dummymailsender.testing@gmail.com", "231292231292");
		transport.sendMessage(packet.getMessage(), packet.getMessage().getAllRecipients());
		transport.close();
		
	}
	public static void sendMail(MessageRequest msgrecieved) throws AddressException, MessagingException {
		PacketForTransport packet=generateConfiguredMessageForSendingMail(msgrecieved);
		generateConfiguredTransportForMail(packet);
		
	}
}
