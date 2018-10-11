package com.notify.senders;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.notify.domain.MessageRequest;

public interface NotificationSender {
	
	 boolean  sendMessage(MessageRequest message) throws AddressException, MessagingException;

}
