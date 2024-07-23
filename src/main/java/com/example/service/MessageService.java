package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import java.util.*;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private AccountRepository accountRepository;
    
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

    public Message createMessage(Message message){
        messageRepository.save(message);
        return message;
        // if(message.getMessageText().length() >0 && message.getMessageText().length() <= 255 && accountRepository.findById(message.getPostedBy()) != null){
        //     messageRepository.save(message);
        //     return message;
        // }else{
        //     return null;
        // }
    }
}
