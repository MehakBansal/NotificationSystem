package com.notify.domain;

import java.io.Serializable;
import java.sql.Date;

public class MessageRequest implements Serializable{
	 
    
	private static final long serialVersionUID = 1L;
	
	private String sender;
	private String to;
	private String message;
	private String noticationType;
	private String subject;
 
	
	
	public String getSender() {
		return sender;
		
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public String getNoticationType() {
		return noticationType;
	}
	public void setNoticationType(String noticationType) {
		this.noticationType = noticationType;
	}
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
	
	public MessageRequest(String sender, String to, String message) {
		super();
		this.sender = sender;
		this.to = to;
		this.message = message;
	}
	public MessageRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
 
    
}