package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.InvalidLoginException;

import java.util.*;

@Service
public class AccountService {
   
    private AccountRepository accountRepository;
    private MessageRepository messageRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, MessageRepository messageRepository){
        this.accountRepository =accountRepository;
        this.messageRepository = messageRepository;
    }
    
    public Account loginUser(String username, String password){
       Optional<Account> ac = accountRepository.findByUsername(username);
       if(ac.isPresent()){
            Account found = ac.get();
            if(found.getPassword().equals(password)) return found;
       }
       throw new InvalidLoginException("Invalid Login");
    }

    public Account registerUser(Account account){
        return accountRepository.save(account);
    }

    public List<Message> retrieveAllMessagesForUser(int id){
        List<Message> messages = messageRepository.findAllBypostedBy(id);
        
        return messages;
    }
 }
