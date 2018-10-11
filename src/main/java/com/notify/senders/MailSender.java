package com.notify.senders;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.notify.controllers.MessageForMailConfiguration;
import com.notify.domain.MessageRequest;



public class MailSender implements NotificationSender {

	@Override
	public boolean sendMessage(MessageRequest msgrecieved) throws AddressException, MessagingException {
		// TODO Auto-generated method stub
		System.out.println("Sending Mail ----------->>>>>" + msgrecieved.getMessage());
		MessageForMailConfiguration.sendMail(msgrecieved);

		System.out.println("Done --->Mail Sent!!!!");
		return true;
	}

	
	
}
		

	
