package org.example.stockmarket.stocks.stock.repository;

import org.example.stockmarket.stocks.stock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, String> {
}
