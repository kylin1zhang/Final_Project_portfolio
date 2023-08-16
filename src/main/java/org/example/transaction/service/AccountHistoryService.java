package org.example.transaction.service;

import lombok.AllArgsConstructor;
import org.example.stockmarket.market.entity.Market;
import org.example.stockmarket.market.service.MarketService;
import org.example.stockmarket.stocks.stock.utils.SortHistory;
import org.example.transaction.model.entity.AccountHistory;
import org.example.transaction.repository.AccountHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountHistoryService {

    @Autowired
    private final AccountHistoryRepository accountHistoryRepository;
    @Autowired
    private final AccountService accountService;
    @Autowired
    private final MarketService marketService;

    public void saveDailyAccountHistory() {
        Market market = marketService.findMarketEntity();
        accountService.findAllAccounts().forEach(account -> accountHistoryRepository.save(
                new AccountHistory(market.getDate(), account, account.getTotalProfits()
                )));
    }

    public List<AccountHistory> findHistoryOfAccount(String username) {
        List<AccountHistory> accountHistory =
                accountService.getAccountByName(username).getAccountHistory();
        SortHistory.sortAccountHistoryByDate(accountHistory);
        return accountHistory;
    }

    @Transactional
    public void truncateAccountHistoryAtEndOfYear() {
        accountHistoryRepository.truncateTable();
    }
}
