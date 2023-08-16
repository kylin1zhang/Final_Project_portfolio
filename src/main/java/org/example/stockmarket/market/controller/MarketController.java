package org.example.stockmarket.market.controller;

import lombok.AllArgsConstructor;
import org.example.stockmarket.market.entity.Market;
import org.example.stockmarket.market.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/market")
@AllArgsConstructor
public class MarketController {

    @Autowired
    private final MarketService marketService;

    @RequestMapping()
    public Market findMarketEntity() {
        return marketService.findMarketEntity();
    }
}
