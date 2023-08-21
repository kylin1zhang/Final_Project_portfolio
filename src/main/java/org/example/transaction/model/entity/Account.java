package org.example.transaction.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.transaction.utils.CalculateCostBasisAndProfits;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/*
    Class that is used to buy/sell stocks on the market.
    Keeps a total balance that is used for stock trading, as well as a
    Set of Owned Stocks (Stock Ticker, Amount Owned)
 */
@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
public class Account implements Serializable {

    @Id
    private String username;

    @Column(name = "balance")
    private Double accountBalance;

    @Column(name = "total_profits")
    private Double totalProfits;

    public Account(String username) {
        this.username = username;
        this.accountBalance = 10_000.0;
        this.totalProfits = 0.0;
    }

    public void updateTotalProfits(double costBasis, int sharesToSell, double currentPrice) {
        if (this.totalProfits == null) this.totalProfits = 0.0;
        setTotalProfits(CalculateCostBasisAndProfits.findProfitsAfterSelling(
                this.totalProfits, costBasis, sharesToSell, currentPrice
        ));
    }

    public void updateAccountBalance(double amountToAdd){
        this.setAccountBalance(Math.round((this.getAccountBalance() + amountToAdd) * 100.00) / 100.00);
    }
}
