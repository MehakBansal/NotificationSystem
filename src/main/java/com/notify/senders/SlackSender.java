package com.notify.senders;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;


import com.notify.domain.MessageRequest;

import org.apache.http.HttpEntity;




public class SlackSender implements NotificationSender {
	
	@Override
	public boolean sendMessage(MessageRequest message) throws AddressException, MessagingException{
		// TODO Auto-generated method stub
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("https://hooks.slack.com/services/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");
		httppost.addHeader("Content-type", "application/json");
		StringEntity params = new StringEntity("{\"text\" : \""+message.getMessage()+"\"}","UTF-8");
		params.setContentType("application/json");
	
		httppost.setEntity(params);
		HttpResponse response = null;
		try {
			response = httpclient.execute(httppost);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpEntity entity = response.getEntity();
	    
	    
		System.out.println("Sending message on slack ----------->>>>>" + message.getMessage());
		return true;
	}

}
