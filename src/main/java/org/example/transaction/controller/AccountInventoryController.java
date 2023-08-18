package org.example.transaction.controller;

import lombok.AllArgsConstructor;
import org.example.stockmarket.stocks.stock.service.StockService;
import org.example.transaction.model.entity.Account;
import org.example.transaction.model.entity.StockOwned;
import org.example.transaction.model.payload.BuyStockRequest;
import org.example.transaction.model.payload.SellStockRequest;
import org.example.transaction.service.AccountService;
import org.example.transaction.service.StockOwnedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/inventory")
@AllArgsConstructor
public class AccountInventoryController {

    @Autowired
    private StockOwnedService stockOwnedService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private StockService stockService;

    @PostMapping(value = "/buy")
    public void buyNewStock(@RequestBody BuyStockRequest buyStock)
            throws javax.security.auth.login.AccountNotFoundException {
        stockOwnedService.buyStock(buyStock);
    }

    @PostMapping(value = "/sell")
    public void sellStockInInventory(@RequestBody SellStockRequest sellStock)
            throws javax.security.auth.login.AccountNotFoundException {
        stockOwnedService.sellStock(sellStock);
    }

    @RequestMapping(value = "/stock-owned/get/{username}")
    public List<StockOwned> getAllStockOwnedByUsername(@PathVariable String username) throws AccountNotFoundException {
        Account account = accountService.getAccountByName(username);
        List<StockOwned> stockOwned = stockOwnedService.findStocksOwnedByAccount(account);
        return stockOwned;
    }

}
