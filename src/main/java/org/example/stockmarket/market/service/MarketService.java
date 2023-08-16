package org.example.stockmarket.market.service;

import lombok.AllArgsConstructor;
import org.example.stockmarket.market.entity.Market;
import org.example.stockmarket.market.enums.MarketTrajectory;
import org.example.stockmarket.market.repository.MarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
@AllArgsConstructor
public class MarketService {

    @Autowired
    private final MarketRepository marketRepository;

    public Market findMarketEntity() {
        Market market = marketRepository.findById(1).orElse(null);
        if (market == null) {
            LocalDate localDate = LocalDate.of(2023, 05, 1);
            LocalTime localTime = LocalTime.of(00, 00);
            ZoneId zoneId = ZoneId.of("GMT+01:00");
            var startingTime = ZonedDateTime.of(localDate, localTime, zoneId);

            market = new Market(startingTime, 41.0, MarketTrajectory.NORMAL);
            saveMarketEntity(market);
        }
        return market;
    }

    public void saveMarketEntity(Market market) {
        if (market == null)
            return;
        marketRepository.save(market);
    }
}
