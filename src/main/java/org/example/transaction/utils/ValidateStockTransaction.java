package org.example.transaction.utils;

import org.example.stockmarket.stock.entity.Stock;
import org.example.stockmarket.stock.service.StockService;
import org.example.transaction.model.entity.Account;
import org.example.transaction.model.payload.BuyStockRequest;

public class ValidateStockTransaction {


    public static boolean doesAccountHaveEnoughMoney(Account account,
                                                     BuyStockRequest buyStockRequest,
                                                     StockService stockService) {
        double balance = account.getAccountBalance();
        Stock stock;
        try {
            stock = stockService.getStockByTickerSymbol(buyStockRequest.getTicker());
        } catch (Exception ex) {
            return false;
        }
        return balance > (stock.getPrice() * buyStockRequest.getSharesToBuy());
    }
}
