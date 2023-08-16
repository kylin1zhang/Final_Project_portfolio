package org.example.transaction.controller;

import lombok.AllArgsConstructor;
import org.example.transaction.model.entity.Account;
import org.example.transaction.model.payload.AccountTransaction;
import org.example.transaction.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;

@RestController
@RequestMapping(value = "/api/v1/account")
@AllArgsConstructor
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "{username}")
    public Account getAccountByUsername(@PathVariable String username) throws javax.security.auth.login.AccountNotFoundException {
        return accountService.getAccountByName(username);
    }

    @PostMapping(value = "{username}")
    public void createAccount(@PathVariable String username) {
        if(username.equalsIgnoreCase("trading-bot")){
            throw new NullPointerException("Cannot Create Account With Invalid Username");
        }
        accountService.createNewAccount(username);
    }

    @PostMapping(value = "/deposit")
    public void depositToAccount(@RequestBody AccountTransaction accountTransaction) throws AccountNotFoundException {
        accountService.updateBalanceAndSave(accountTransaction);
    }
}
