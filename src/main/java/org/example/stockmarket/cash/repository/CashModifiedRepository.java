package org.example.stockmarket.cash.repository;

import org.example.stockmarket.cash.entity.CashModified;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CashModifiedRepository  extends JpaRepository<CashModified, Integer> {
    public List<CashModified> findByName(String name);
}
