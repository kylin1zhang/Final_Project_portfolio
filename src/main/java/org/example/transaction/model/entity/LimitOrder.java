package org.example.transaction.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.stockmarket.stocks.stock.entity.Stock;

import java.io.Serializable;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class LimitOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Account account;

    @OneToOne(cascade = CascadeType.ALL)
    private Stock stock;

    @Column
    private Integer sharesToBuy;

    @Column
    private Double limitPrice;

    public LimitOrder(Account account, Stock stock, int sharesToBuy, double limitPrice) {
        this.account = account;
        this.sharesToBuy = sharesToBuy;
        this.stock = stock;
        this.limitPrice = limitPrice;
    }

    public boolean validOrderRequest() {
        return !(sharesToBuy * stock.getPrice() > account.getAccountBalance());
    }
}
