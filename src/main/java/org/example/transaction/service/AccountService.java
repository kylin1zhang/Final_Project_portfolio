package org.example.transaction.service;

import lombok.AllArgsConstructor;
import org.example.transaction.model.entity.Account;
import org.example.transaction.model.payload.AccountTransaction;
import org.example.transaction.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountByName(String username) throws javax.security.auth.login.AccountNotFoundException {
        return accountRepository.findById(username).get();
    }

    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

    public void createNewAccount(String username) {
        accountRepository.save(new Account(username));
    }

    public boolean accountExists(String username) {
        try {
            getAccountByName(username);
            return true;
        } catch (javax.security.auth.login.AccountNotFoundException ex) {
            return false;
        }
    }

    public void updateBalanceAndSave(AccountTransaction accountTransaction)
            throws AccountNotFoundException {
        Account account = getAccountByName(accountTransaction.getUsername());
        account.updateAccountBalance(accountTransaction.getAmountToAdd());
        saveAccount(account);
    }

    public void updateBalanceAndSave(Account account, double amountToAdd) {
        account.updateAccountBalance(amountToAdd);
        saveAccount(account);
    }
}
