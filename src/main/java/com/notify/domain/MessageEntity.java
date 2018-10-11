package com.notify.domain;

import java.util.Date;
import javax.persistence.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
@Table(name = "messages")
public class MessageEntity {
	

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="message_id")
	private int messageID;
	@Column(name="sender")
	private String sender;
	@Column(name="receiver")
	private String receiver;
	@Column(name="message")
	private String message;
	@Column(name="notification_type")
	private String noticationType;
	@Column(name="message_subject")
	private String messageSubject;
	@Column(name="msg_status")
	private String messageStatus;
	@Column(name="creation_date")
	private Date creationDate;
	@Column(name="message_referenceid")
	private String messageReferenceID;
 
	public int getMessageID() {
		return messageID;
	}
	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}
	
	public String getSender() {
		return sender;
		
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getTo() {
		return receiver;
	}
	public void setTo(String to) {
		this.receiver = to;
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
		return messageSubject;
	}

	public void setSubject(String subject) {
		this.messageSubject = subject;
	}
	
	public String getStatus() {
		return messageStatus;
	}
	public void setStatus(String status) {
		this.messageStatus = status;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date date) {
		this.creationDate = date;
	}
	public String getMessageReferenceID() {
		return messageReferenceID;
	}
	public void setMessageReferenceID(String messageReferenceID) {
		this.messageReferenceID = messageReferenceID;
	}
	public MessageEntity(String sender, String to, String message) {
		super();
		this.sender = sender;
		this.receiver = to;
		this.message = message;
	}
	public MessageEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
