package com.sep3.javaapplicationserver.controller;

import com.sep3.javaapplicationserver.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sep3.javaapplicationserver.model.Account;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

@RestController
@RequestMapping("/account")
public class AccountController {

    public final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("")
    public ResponseEntity<String> addNewAccount(@RequestBody Account account) {
        ResponseEntity<String> entity;
        try {
            accountService.addNewAccount(account);
            entity = new ResponseEntity<>("ok",HttpStatus.OK);
        }catch (Exception e){
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            System.out.println(entity.getBody());
        }
        return entity;
    }

    @PutMapping("")
    public ResponseEntity<String> editAccount(@RequestBody Account account){
        ResponseEntity<String> response;

        try {
            accountService.editAccount(account);
            response = new ResponseEntity<String>("Account edited successfully", HttpStatus.OK);
        }
        catch (Exception e){
            response = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return response;
    }
}
