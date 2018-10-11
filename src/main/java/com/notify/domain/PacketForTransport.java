package com.notify.domain;

import javax.mail.Message;
import javax.mail.Session;

public class PacketForTransport {

	
	private Message message;
	
	private Session session;

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
}
