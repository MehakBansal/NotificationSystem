package com.notify.repositories;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.notify.domain.MessageEntity;







public interface MessageRepository extends CrudRepository<MessageEntity, Integer> {
	
	@Query("SELECT p FROM MessageEntity p WHERE LOWER(p.messageReferenceID) = LOWER(:msgId)")
    public List<MessageEntity> findByMessageId(@Param("msgId") String msgId);
}
