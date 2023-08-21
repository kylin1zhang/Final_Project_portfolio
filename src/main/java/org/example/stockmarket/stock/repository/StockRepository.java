package org.example.stockmarket.stock.repository;

import org.example.stockmarket.stock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, String> {
}
