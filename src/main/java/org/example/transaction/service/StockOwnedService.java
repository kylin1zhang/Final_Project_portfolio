package org.example.transaction.service;

import lombok.AllArgsConstructor;
import org.example.stockmarket.stock.entity.Stock;
import org.example.stockmarket.stock.service.StockService;
import org.example.transaction.model.entity.Account;
import org.example.transaction.model.entity.StockOwned;
import org.example.transaction.model.entity.StockTransaction;
import org.example.transaction.model.payload.BuyStockRequest;
import org.example.transaction.model.payload.SellStockRequest;
import org.example.transaction.repository.StockOwnedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class StockOwnedService {

    @Autowired
    private StockOwnedRepository stockOwnedRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private StockService stockService;

    public void buyStock(BuyStockRequest buyStock) throws AccountNotFoundException {
        Account account = accountService.getAccountByName(buyStock.getUsername());
        Stock stock = stockService.getStockByTickerSymbol(buyStock.getTicker());
        StockOwned stockOwned = findStockOwned(account, stock);

        if (stockOwned != null) {
            // Existing stock ownership, update the stockOwned entity and add transaction
            // ... (your existing code)

            StockTransaction transaction = new StockTransaction();
            transaction.setStockOwned(stockOwned);
            transaction.setAmount(buyStock.getSharesToBuy());
            transaction.setTimestamp(LocalDateTime.now());
            transaction.setBuySell("buy");
            transaction.setPrice(stock.getPrice());

            stockOwned.getTransactionHistory().add(transaction);
            stockOwnedRepository.save(stockOwned);  // Make sure to save the updated stockOwned
            return;
        }

        // New stock ownership, create a new stockOwned entity
        // ... (your existing code)

        // Create a new stockOwned entity and add the transaction
        stockOwned = new StockOwned(account, buyStock.getTicker(), buyStock.getSharesToBuy(), stock.getPrice());

        StockTransaction transaction = new StockTransaction();
        transaction.setStockOwned(stockOwned);
        transaction.setAmount(buyStock.getSharesToBuy());
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setBuySell("buy");
        transaction.setPrice(stock.getPrice());

        stockOwned.getTransactionHistory().add(transaction);

        stockOwnedRepository.save(stockOwned);
    }


    public void saveNewStockOwned(BuyStockRequest buyStock, Account account, double stockPrice) {
        stockOwnedRepository.save(new StockOwned(
                account, buyStock.getTicker(), buyStock.getSharesToBuy(), stockPrice));
    }

    public void sellStock(SellStockRequest sellStock) throws AccountNotFoundException {

        Account account = accountService.getAccountByName(sellStock.getUsername());
        Stock stock = stockService.getStockByTickerSymbol(sellStock.getTicker());
        StockOwned stockOwned = findStockOwned(account, stock);
        account.updateTotalProfits(
                stockOwned.getCostBasis(),
                sellStock.getSharesToSell(),
                stock.getPrice());
        accountService.updateBalanceAndSave(account, stock.getPrice() * sellStock.getSharesToSell());
        if (sellStock.getSharesToSell() - stockOwned.getAmountOwned() == 0) {
            clearAndDeleteStockOwned(stockOwned);
        } else {
            stockOwned.setAmountOwned(stockOwned.getAmountOwned() - sellStock.getSharesToSell());

            StockTransaction transaction = new StockTransaction();
            transaction.setStockOwned(stockOwned);
            transaction.setAmount(sellStock.getSharesToSell());
            transaction.setTimestamp(LocalDateTime.now());
            transaction.setBuySell("sell");
            transaction.setPrice(stock.getPrice());

            stockOwned.getTransactionHistory().add(transaction);

            stockOwnedRepository.save(stockOwned);
        }


    }

    public StockOwned findStockOwned(Account account, Stock stock) {
        return stockOwnedRepository.findAll().stream()
                .filter(stockOwned -> stockOwned.getTicker().equalsIgnoreCase(stock.getTicker()))
                .filter(stockOwned -> stockOwned.getAccount().getUsername().equals(account.getUsername()))
                .findFirst()
                .orElse(null);
    }
    public List<StockOwned> findStocksOwnedByAccount(Account account) {
        return stockOwnedRepository.findAll();
    }

    public void clearAndDeleteStockOwned(StockOwned stockOwned) {
        stockOwnedRepository.delete(stockOwned);
    }
}
