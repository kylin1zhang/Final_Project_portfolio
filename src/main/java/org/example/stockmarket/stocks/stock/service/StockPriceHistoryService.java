package org.example.stockmarket.stocks.stock.service;

import lombok.AllArgsConstructor;
import org.example.stockmarket.market.entity.Market;
import org.example.stockmarket.market.service.MarketService;
import org.example.stockmarket.stocks.stock.entity.StockPriceHistory;
import org.example.stockmarket.stocks.stock.entity.idclass.StockPriceHistoryId;
import org.example.stockmarket.stocks.stock.repository.StockPriceHistoryRepository;
import org.example.stockmarket.stocks.stock.utils.SortHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StockPriceHistoryService {

    @Autowired
    private final StockPriceHistoryRepository stockPriceHistoryRepository;
    @Autowired
    private final StockService stockService;
    @Autowired
    private final MarketService marketService;

    public void saveStockHistoryDaily() {
        Market market = marketService.findMarketEntity();
        stockService.getAllStocks().forEach(stock ->
                stockPriceHistoryRepository.save(new StockPriceHistory(
                        new StockPriceHistoryId(market.getDate(), stock.getTicker()),
                        stock,
                        stock.getPrice())));
    }

    public List<StockPriceHistory> findStockHistoryByTicker(String ticker) {
        List<StockPriceHistory> stockPriceHistory = stockPriceHistoryRepository.findAll().stream()
                .filter(history -> history.getStock().getTicker().equalsIgnoreCase(ticker))
                .collect(Collectors.toList());
        SortHistory.sortStockHistoryByDate(stockPriceHistory);
        return stockPriceHistory;
    }

    @Transactional
    public void truncateStockHistoryAtEndOfYear() {
        stockPriceHistoryRepository.truncateTable();
    }
}
