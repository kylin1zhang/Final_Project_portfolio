package org.example.transaction.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "stock_transaction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockTransaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_owned_id", nullable = false)
    @JsonIgnore
    private StockOwned stockOwned;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "buy_sell")
    private String buySell;

    @Column(name = "price")
    private Double price;
}

