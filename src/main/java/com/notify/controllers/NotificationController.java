package com.notify.controllers;
import com.notify.services.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



import com.notify.domain.AsynchResponseStatus;
import com.notify.domain.MessageEntity;
import com.notify.domain.MessageRequest;
import com.notify.domain.ResponseStatus;
import com.notify.senders.NotificationSender;
import com.notify.senders.NotificationSenderFactory;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;


@Controller
public class NotificationController {
	
	private IMessageService messageService; 
	
    @Autowired
    public void setProductService(IMessageService messageService) {
        this.messageService = messageService;
    }
	
	
	private static final Queue<MessageEntity> messagingQueue= new LinkedList<MessageEntity>();
 
    @RequestMapping("/")
    public String welcome() {//Welcome page, non-rest
        return "Welcome to RestTemplate Example.";
    }
 
    @RequestMapping("/getStatusOfReq/{msgRefID}")
    public @ResponseBody AsynchResponseStatus getStatus(@PathVariable String msgRefID) {//REST Endpoint.
 
    	
    		String status = messageService.getMessageReqStatus(msgRefID);
       
    		AsynchResponseStatus asynchStatus= new AsynchResponseStatus();
    		
    		if(status!=null)
    		asynchStatus.setStatus(status);
    		
    		else
    			asynchStatus.setStatus("The Message Request has not been received till now");
        return asynchStatus;
    }
    @RequestMapping(value = "/sendNotification", method = RequestMethod.POST ,consumes="application/json",produces="application/json")
	public @ResponseBody ResponseStatus createNewMessage(@RequestBody MessageRequest msg) {
    		MessageEntity msgEntity = new MessageEntity();
    		msgEntity.setCreationDate(new Date());
    		msgEntity.setMessage(msg.getMessage());
    		msgEntity.setNoticationType(msg.getNoticationType());
    		msgEntity.setSender(msg.getSender());
    		msgEntity.setStatus("IN_PROGRESS");
    		msgEntity.setSubject(msg.getSubject());
    		msgEntity.setTo(msg.getTo());
    		String msgRefID=UUID.randomUUID().toString();
    		msgEntity.setMessageReferenceID(msgRefID);
    		
    		boolean flag = messageService.addMessage(msgEntity);
		NotificationSender notificationSender= NotificationSenderFactory.getSender(msg.getNoticationType());
		ResponseStatus responseStatus= new ResponseStatus();
		try {
			if(notificationSender.sendMessage(msg)) responseStatus.setStatus(true);
		}
		
		catch(Exception e) {
			responseStatus.setStatus(false);
			System.out.print(e);
		} 
		if(responseStatus.isStatus()) 
			messageService.updateMessage("SUCCESS",msgRefID);
		else messageService.updateMessage("FAILED",msgRefID);
		return responseStatus;
	}
    
    
    
    @RequestMapping(value = "/sendNotificationinBulk", method = RequestMethod.POST ,consumes="application/json",produces="application/json")
   	public @ResponseBody AsynchResponseStatus processMessagesInBulk(@RequestBody MessageRequest msg) {
       		MessageEntity msgEntity = new MessageEntity();
       		msgEntity.setCreationDate(new Date());
       		msgEntity.setMessage(msg.getMessage());
       		msgEntity.setNoticationType(msg.getNoticationType());
       		msgEntity.setSender(msg.getSender());
       		msgEntity.setStatus("IN_PROGRESS");
       		msgEntity.setSubject(msg.getSubject());
       		msgEntity.setTo(msg.getTo());
       		String msgRefID=UUID.randomUUID().toString();
       		msgEntity.setMessageReferenceID(msgRefID);
       		
       		messagingQueue.add(msgEntity);
       		AsynchResponseStatus status= new AsynchResponseStatus();
   		
       		status.setStatus("http://localhost:8080/getStatusOfReq/"+msgRefID);
   		
   		
       		return status;
   	}
       
    @RequestMapping(value = "/updateNotificationinBulk", method = RequestMethod.POST ,consumes="application/json",produces="application/json")
   	public @ResponseBody AsynchResponseStatus updateMsgReqInDBScheduler() {
    
    		while(!messagingQueue.isEmpty()) {
    			MessageEntity msgEntity = messagingQueue.poll();
    			boolean flag = messageService.addMessage(msgEntity);
    			NotificationSender outputObject= NotificationSenderFactory.getSender(msgEntity.getNoticationType());
    			MessageRequest msgReq = new MessageRequest();
    			msgReq.setMessage(msgEntity.getMessage());
    			msgReq.setNoticationType(msgEntity.getNoticationType());
    			msgReq.setSender(msgEntity.getSender());
    			msgReq.setSubject(msgEntity.getSubject());
    			msgReq.setTo(msgEntity.getTo());
    			ResponseStatus status= new ResponseStatus();
    			try {
    				if(outputObject.sendMessage(msgReq)) status.setStatus(true);
    			}
    			
    			catch(Exception e) {
    				status.setStatus(false);
    				System.out.print(e);
    			} 
    			if(status.isStatus()) 
    				messageService.updateMessage("SUCCESS",msgEntity.getMessageReferenceID());
    			else messageService.updateMessage("FAILED",msgEntity.getMessageReferenceID());
    			
    		}
    		AsynchResponseStatus asynchStatus= new AsynchResponseStatus();
			asynchStatus.setStatus("updated all msg Requests");
			return asynchStatus;
    }
}
