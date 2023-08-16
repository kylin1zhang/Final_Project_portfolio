package org.example.transaction.controller;

import lombok.AllArgsConstructor;
import org.example.stockmarket.stocks.stock.service.StockService;
import org.example.transaction.model.entity.LimitOrder;
import org.example.transaction.model.payload.BuyStockRequest;
import org.example.transaction.model.payload.LimitOrderRequest;
import org.example.transaction.model.payload.SellStockRequest;
import org.example.transaction.service.AccountService;
import org.example.transaction.service.LimitOrderService;
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
    private LimitOrderService limitOrderService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private StockService stockService;

    @PostMapping(value = "/buy/market")
    public void buyNewStock(@RequestBody BuyStockRequest buyStock)
            throws javax.security.auth.login.AccountNotFoundException {
        stockOwnedService.buyStock(buyStock);
    }

    @PostMapping(value = "/sell")
    public void sellStockInInventory(@RequestBody SellStockRequest sellStock)
            throws javax.security.auth.login.AccountNotFoundException {
        stockOwnedService.sellStock(sellStock);
    }

    @PostMapping(value = "/buy/limit")
    public List<LimitOrder> limitOrder(@RequestBody LimitOrderRequest request) throws javax.security.auth.login.AccountNotFoundException {
        limitOrderService.saveLimitOrder(new LimitOrder(
                accountService.getAccountByName(request.getUsername()),
                stockService.getStockByTickerSymbol(request.getTicker()),
                request.getSharesToBuy(), request.getLimitPrice()));

        return limitOrderService.findLimitOrdersByAccount(
                accountService.getAccountByName("default"));
    }

    @RequestMapping(value = "/orders/get/{username}")
    public List<LimitOrder> getAllLimitOrdersByUsername(@PathVariable String username) throws AccountNotFoundException {
        return limitOrderService.findLimitOrdersByAccount(accountService.getAccountByName(username));
    }
}
