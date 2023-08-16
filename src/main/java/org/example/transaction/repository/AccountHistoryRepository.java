package org.example.transaction.repository;

import org.example.transaction.model.entity.AccountHistory;
import org.example.transaction.model.entity.idclass.AccountHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AccountHistoryRepository extends JpaRepository<AccountHistory, AccountHistoryId> {

    @Modifying
    @Query(value = "truncate table account_history", nativeQuery = true)
    void truncateTable();
}
