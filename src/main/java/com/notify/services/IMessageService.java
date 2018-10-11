package com.notify.services;

import com.notify.domain.MessageEntity;

public interface IMessageService {
	
     boolean addMessage(MessageEntity message);
     
     boolean updateMessage(String status,String messageRefID);
     
     String getMessageReqStatus(String msgRefID);

}
