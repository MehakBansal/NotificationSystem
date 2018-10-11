package com.notify.domain;

import java.io.Serializable;

public class AsynchResponseStatus implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	String status;
	public AsynchResponseStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
