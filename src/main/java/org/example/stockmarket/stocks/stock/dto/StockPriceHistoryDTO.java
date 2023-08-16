package org.example.stockmarket.stocks.stock.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.stockmarket.stocks.stock.entity.StockPriceHistory;

import java.time.ZonedDateTime;

@Getter
@Setter
public class StockPriceHistoryDTO {

    
    private ZonedDateTime  marketDate;
    private double stockPrice;

    public StockPriceHistoryDTO(StockPriceHistory history){
        this.marketDate = history.getId().getMarketDate();
        this.stockPrice = history.getStockPrice();
    }
}
