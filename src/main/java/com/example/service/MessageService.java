package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.exception.InvalidMessageException;
import com.example.exception.MessageNotFoundException;
import com.example.repository.MessageRepository;



import java.util.*;

@Service
@Lazy
public class MessageService {
    
    private MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository  = messageRepository;
    }
    
    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public Message getMessageById(int id){
        return messageRepository.findById(id).orElse(null);
    }

    public int deleteMessageById(int id){
        Message message = this.getMessageById(id);
        if(message != null){
            messageRepository.deleteById(id);
            return 1;
        }
        return 0;
    }

    public Message updateMessageById(int id, Message message){
        
      
        Optional<Message> msg = messageRepository.findById(id);
        if(msg.isPresent()){
           
            if (message.getMessageText().isEmpty()) {
                throw new InvalidMessageException("Message invalid.");
            }

            Message temp = msg.get();
            temp.setMessageText(message.getMessageText());
            return messageRepository.save(temp);
           
        }
        return null;
        
        
        
    }

    public Message createMessage(Message message) {
        if(message.getMessageText().length() == 0) throw new InvalidMessageException("test");
        Message msg= messageRepository.save(message);
        return msg;
    }

   
}
