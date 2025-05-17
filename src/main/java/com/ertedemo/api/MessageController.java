package com.ertedemo.api;

import com.ertedemo.api.resource.messages.MessageRequest;
import com.ertedemo.api.resource.messages.MessageResponse;
import com.ertedemo.domain.model.entites.Landlord;
import com.ertedemo.domain.model.entites.Message;
import com.ertedemo.domain.model.entites.Tenant;
import com.ertedemo.domain.services.LandlordService;
import com.ertedemo.domain.services.MessageService;
import com.ertedemo.domain.services.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private LandlordService landlordService;

    @Autowired
    private TenantService tenantService;

    @PostMapping
    public ResponseEntity<String> createMessage(@RequestBody MessageRequest messageRequest) {
        Optional<Landlord> author = landlordService.getById(messageRequest.getAuthorId());
        Optional<Tenant> recipient = tenantService.getById(messageRequest.getRecipientId());

        if (author.isPresent() && recipient.isPresent()) {
            Message message = new Message(author.get(), recipient.get(), messageRequest);
            messageService.create(message);
            return ResponseEntity.ok("Message added successfully.");
        }
        return ResponseEntity.badRequest().body("Error to add message");
    }

    @GetMapping("/recipient/{recipientId}")
    public ResponseEntity<List<MessageResponse>> getMessageByRecipientId(@PathVariable Long recipientId) {
        Optional<Tenant> recipient = tenantService.getById(recipientId);

        if (recipient.isPresent()) {
            List<MessageResponse> messages = messageService.getByRecipient(recipient.get()).stream()
                    .map(message -> new MessageResponse(message))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(messages);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}