package org.example.stockmarket.market.service;

import lombok.AllArgsConstructor;
import org.example.stockmarket.market.entity.Market;
import org.example.stockmarket.market.repository.MarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class MarketService {

    @Autowired
    private final MarketRepository marketRepository;

    public Market findMarketEntity() {
        return marketRepository.findById(1).orElse(null);
    }

    public void saveMarketEntity(Market market) {
        if (market == null)
            return;
        marketRepository.save(market);
    }
}
