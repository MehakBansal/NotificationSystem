package com.notify.repositories;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.notify.domain.MessageEntity;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MessageRepositoryTest {

   

    @Autowired
    private MessageRepository messageRepository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testPersistence() {
       
     	MessageEntity msgEntity = new MessageEntity();
        msgEntity.setSender("sender");
        msgEntity.setTo("receiver");
        msgEntity.setMessage("hey");
        msgEntity.setMessageReferenceID("ref1");
        msgEntity.setCreationDate(new Date());
        msgEntity.setMessageID(1);
        msgEntity.setSubject("subject");
        
        Assert.assertNotNull(msgEntity.getMessageID());

       
        messageRepository.save(msgEntity);

       
        
        MessageEntity newProduct = messageRepository.findById(msgEntity.getMessageID()).orElse(null);
      
        Assert.assertEquals("ref1", newProduct.getMessageReferenceID());
      
    }
}