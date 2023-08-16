package org.example.transaction.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.transaction.model.entity.idclass.AccountHistoryId;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
@Table(name = "account_history")
@Getter
@Setter
@NoArgsConstructor
@IdClass(AccountHistoryId.class)
@AllArgsConstructor
public class AccountHistory implements Serializable {

    @Id        
    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime date;   

    @Id
    @JsonBackReference
    private Account account;

    @Column(name = "account_balance")
    private Double balance;
}
