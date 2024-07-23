package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.AccountRepository;
import com.example.entity.Account;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account loginUser(String username, String password){
        return null;
    }

    public Account registerUser(Account account){
        return accountRepository.save(account);
    }
}
