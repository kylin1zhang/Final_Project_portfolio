package org.example.stockmarket.stock.configuration;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.example.stockmarket.stock.defaults.DefaultStocksList;
import org.example.stockmarket.stock.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class StockConfiguration {

    @Autowired
    private StockService stockService;

    private final Logger logger = LoggerFactory.getLogger(StockConfiguration.class);

    @PostConstruct
    public void saveStocksToDatabaseOnStartup() {
        if (DefaultStocksList.getCountForDefaultStocks() != stockService.findStockRowCount()) {
            logger.info("Saving Default Stocks");
            stockService.saveDefaultStockToDatabase(DefaultStocksList.getAllDefaultStocks());
        }
    }
}
