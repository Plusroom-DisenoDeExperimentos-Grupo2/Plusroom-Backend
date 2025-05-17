package com.ertedemo.domain.persistence;

import com.ertedemo.domain.model.entites.Message;
import com.ertedemo.domain.model.entites.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByRecipient(Tenant recipient);
}