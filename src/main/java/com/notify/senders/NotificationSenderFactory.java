package com.notify.senders;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

public class NotificationSenderFactory {
	
	public static NotificationSender getSender(String notificationType) {
		if("Mail".equalsIgnoreCase(notificationType)) return getMailSender();
		else if("Slack".equalsIgnoreCase(notificationType)) return getSlackSender();
		return null;
		
		
		
	}

	 @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public static MailSender getMailSender() {
		return new MailSender();
	}
	 @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public static SlackSender getSlackSender() {
		return new SlackSender();
	}
}
