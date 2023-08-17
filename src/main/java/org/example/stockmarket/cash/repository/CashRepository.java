package org.example.stockmarket.cash.repository;

import org.example.stockmarket.cash.entity.Cash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CashRepository extends JpaRepository<Cash,Integer> {
    public List<Cash> findByName(String name);
}
