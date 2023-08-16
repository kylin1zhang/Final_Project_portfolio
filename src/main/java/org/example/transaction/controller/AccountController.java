package org.example.transaction.controller;

import lombok.AllArgsConstructor;
import org.example.transaction.exception.AccountBalanceException;
import org.example.transaction.exception.AccountNotFoundException;
import org.example.transaction.exception.InvalidAccountException;
import org.example.transaction.model.entity.Account;
import org.example.transaction.model.entity.AccountHistory;
import org.example.transaction.model.payload.AccountTransaction;
import org.example.transaction.service.AccountHistoryService;
import org.example.transaction.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/account")
@AllArgsConstructor
public class AccountController {

    @Autowired
    private final AccountService accountService;
    @Autowired
    private final AccountHistoryService accountHistoryService;

    @RequestMapping(value = "{username}")
    public Account getAccountByUsername(@PathVariable String username) throws AccountNotFoundException {
        return accountService.getAccountByName(username);
    }

    @PostMapping(value = "{username}")
    public void createAccount(@PathVariable String username) throws InvalidAccountException {
        if(username.equalsIgnoreCase("trading-bot")){
            throw new InvalidAccountException("Cannot Create Account With Invalid Username");
        }
        accountService.createNewAccount(username);
    }

    @PostMapping(value = "/deposit")
    public void depositToAccount(@RequestBody AccountTransaction accountTransaction) throws AccountNotFoundException, AccountBalanceException {
        accountService.updateBalanceAndSave(accountTransaction);
    }

    @GetMapping(value = "/history/{username}")
    public List<AccountHistory> getAccountHistory(@PathVariable String username) throws AccountNotFoundException, AccountBalanceException {
        return accountHistoryService.findHistoryOfAccount(username);
    }
}
