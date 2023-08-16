package org.example.stockmarket.market.repository;

import org.example.stockmarket.market.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketRepository extends JpaRepository<Market, Integer> {
}
