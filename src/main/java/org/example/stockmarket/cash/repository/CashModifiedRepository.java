package org.example.stockmarket.cash.repository;

import org.example.stockmarket.cash.entity.CashModified;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface CashModifiedRepository  extends JpaRepository<CashModified, Integer> {
    public List<CashModified> findByName(String name);
    @Query(value = "select * from cashmodified where modified between ?1 and ?2", nativeQuery = true)
    List<CashModified> findHistory(LocalDateTime startDate, Date endDate);
}
