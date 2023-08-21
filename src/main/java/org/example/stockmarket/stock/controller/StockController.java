package org.example.stockmarket.stock.controller;

import lombok.AllArgsConstructor;
//import org.example.stockmarket.indexfund.utils.Capitalize;
import org.example.stockmarket.stock.entity.Stock;
import org.example.stockmarket.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
This controller provides the endpoints related to stocks on the market.
All individual stocks here will be sent using the StockDto class as that class includes
    news, earnings, and price history.
All lists of stocks (such as sorting by sector ar cap) will be sent using the default
stock class, which ignores news, earnings, and price history fields.
 */
@RestController
@RequestMapping(value = "/api/v1/stocks")
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://127.0.0.1:5501", "http://localhost:8080", "http://localhost:3000"})
@AllArgsConstructor
@SuppressWarnings("unused")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping
    public List<Stock> getAllStockData() {
        return stockService.getAllStocks();
    }

    @GetMapping(value = "/sector/{sector}")
    public List<Stock> getAllStocksBySector(@PathVariable String sector) {
        return stockService.getAllStocksBySector(sector);
    }

    @GetMapping(value = "/price/{ticker}")
    public double getStockPrice(@PathVariable String ticker) {
        return stockService.getStockPriceWithTickerSymbol(ticker);
    }
}