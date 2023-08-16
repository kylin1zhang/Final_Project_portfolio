package org.example.transaction.controller;

import lombok.AllArgsConstructor;
import org.example.stockmarket.stocks.stock.entity.Stock;
import org.example.stockmarket.stocks.stock.service.StockService;
import org.example.transaction.exception.AccountBalanceException;
import org.example.transaction.exception.AccountInventoryException;
import org.example.transaction.exception.AccountNotFoundException;
import org.example.transaction.model.entity.Account;
import org.example.transaction.model.entity.LimitOrder;
import org.example.transaction.model.entity.StockOwned;
import org.example.transaction.model.payload.BuyStockRequest;
import org.example.transaction.model.payload.LimitOrderRequest;
import org.example.transaction.model.payload.SellStockRequest;
import org.example.transaction.service.AccountService;
import org.example.transaction.service.LimitOrderService;
import org.example.transaction.service.StockOwnedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/inventory")
@AllArgsConstructor
public class AccountInventoryController {

    @Autowired
    private final StockOwnedService stockOwnedService;
    @Autowired
    private final LimitOrderService limitOrderService;
    @Autowired
    private final AccountService accountService;
    @Autowired
    private final StockService stockService;


    @PostMapping(value = "/buy/market")
    public void buyNewStock(@RequestBody BuyStockRequest buyStock)
            throws AccountNotFoundException, AccountBalanceException {
        stockOwnedService.buyStock(buyStock);
    }

    @PostMapping(value = "/sell")
    public void sellStockInInventory(@RequestBody SellStockRequest sellStock)
            throws AccountNotFoundException, AccountBalanceException, AccountInventoryException {
        stockOwnedService.sellStock(sellStock);
    }

    @PostMapping(value = "/buy/limit")
    public List<LimitOrder> limitOrder(@RequestBody LimitOrderRequest request) {
        limitOrderService.saveLimitOrder(new LimitOrder(
                accountService.getAccountByName(request.getUsername()),
                stockService.getStockByTickerSymbol(request.getTicker()),
                request.getSharesToBuy(), request.getLimitPrice()));

        return limitOrderService.findLimitOrdersByAccount(
                accountService.getAccountByName("default"));
    }

    @RequestMapping(value = "/orders/get/{username}")
    public List<LimitOrder> getAllLimitOrdersByUsername(@PathVariable String username) {
        return limitOrderService.findLimitOrdersByAccount(accountService.getAccountByName(username));
    }

}
