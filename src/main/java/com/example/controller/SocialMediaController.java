package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import javax.websocket.server.PathParam;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.MessageService;
import com.example.service.AccountService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController

public class SocialMediaController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private AccountService accountService;


    @GetMapping("/login")
    public ResponseEntity<Optional<Account>> loginUser(@RequestBody Account account){
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Account> registerUser(@RequestBody Account account){
        try{
            Account ac = accountService.registerUser(account);
            return new ResponseEntity<>(ac, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        
    }

    @PostMapping("/messages")
    public ResponseEntity<?> createMessage(@RequestBody Message message){
        
        try{
            Message msg = messageService.createMessage(message);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages(){
        return new ResponseEntity<>(messageService.getAllMessages(), HttpStatus.OK);
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable int id){
        Message message = messageService.getMessageById(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/messages/{id}")
    public ResponseEntity<Integer> deleteMessageById(@PathVariable int id){
        int test = messageService.deleteMessageById(id);
        if(test != 0)
        return new ResponseEntity<>(test, HttpStatus.OK);
        else return new ResponseEntity<>(null, HttpStatus.OK);

    }

}
