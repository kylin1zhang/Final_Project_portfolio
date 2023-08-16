package org.example.transaction.model.entity.idclass;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.example.transaction.model.entity.Account;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class AccountHistoryId implements Serializable {

    @Column(name = "market_date")
    private String date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", nullable = false)
    @JsonBackReference
    private Account account;
}
