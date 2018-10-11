package com.notify.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notify.domain.MessageEntity;
import com.notify.domain.MessageRequest;
import com.notify.services.IMessageService;
import org.springframework.http.MediaType;

@RunWith(SpringRunner.class)
@WebMvcTest(NotificationController.class)
public class NotificationControllerTest {
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private IMessageService service;
  

    @Test
    public void getStatusShouldReturnMessageFromService() throws Exception {
        when(service.getMessageReqStatus(Mockito.anyString())).thenReturn("SUCCESS");
        
        this.mockMvc.perform(get("/getStatusOfReq/refid1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("SUCCESS")));
    }
    
    @Test
    public void getStatusShouldReturnNull() throws Exception {
        when(service.getMessageReqStatus(Mockito.anyString())).thenReturn(null);
        
        this.mockMvc.perform(get("/getStatusOfReq/refid1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("The Message Request has not been received till now")));
    }
    
    @Test
    public void createNewMessageTest() throws Exception {
    	 when(service.addMessage(Mockito.any())).thenReturn(true);

  
    	 ObjectMapper mapper = new ObjectMapper();
    	 MessageRequest msg = new MessageRequest();
    	 msg.setMessage("hey");
    	 msg.setNoticationType("Mail");
    	 msg.setSender("dummy");
    	 msg.setTo("dummyto");
    	
    	
    	 String json = mapper.writeValueAsString(msg);
    	 mockMvc.perform(post("/sendNotification")
    			.contentType(MediaType.APPLICATION_JSON)
    	       .content(json)
    	       .accept(MediaType.APPLICATION_JSON))
    	       .andDo(print())
    	       .andExpect(status().isOk())
    	       .andExpect(content().string(containsString("false")));
    	  }
    	
    
}
