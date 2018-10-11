 package com.notify.domain;

import java.io.Serializable;

public class ResponseStatus implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean status;
	public ResponseStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

}
