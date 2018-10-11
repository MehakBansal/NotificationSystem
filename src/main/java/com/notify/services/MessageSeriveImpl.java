package com.notify.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.notify.domain.MessageEntity;
import com.notify.repositories.MessageRepository;

import java.util.List;

@Service
public class MessageSeriveImpl implements IMessageService{
    
	private MessageRepository messageRepository;
  
    @Autowired
    public MessageSeriveImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
        
    }




    @Override
	public synchronized boolean addMessage(MessageEntity message){
     
         	messageRepository.save(message);
            return true;
        }
	
    
    @Override
	public synchronized boolean updateMessage(String status,String messageRefID){
      	List<MessageEntity> msg = messageRepository.findByMessageId(messageRefID);
      
      	MessageEntity message = msg.get(0);
      	message.setStatus(status);
    	    messageRepository.save(message);
        return true;
    }
    
    @Override
	public String getMessageReqStatus(String msgRefID) {
    	List<MessageEntity> msg = messageRepository.findByMessageId(msgRefID);
    	if(msg == null ||   msg.isEmpty())
    		return null;
     MessageEntity message = msg.get(0);
	return message.getStatus();  
	}

}
