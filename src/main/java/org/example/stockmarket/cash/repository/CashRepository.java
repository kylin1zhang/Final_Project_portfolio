package org.example.stockmarket.cash.repository;

import org.example.stockmarket.cash.entity.Cash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashRepository extends JpaRepository<Cash,Integer> {
}
