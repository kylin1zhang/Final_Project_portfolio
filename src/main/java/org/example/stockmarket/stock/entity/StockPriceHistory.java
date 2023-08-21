package org.example.stockmarket.stock.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.stockmarket.stock.entity.StockPriceHistoryId;

import java.io.Serializable;

/*
Saves daily stock history for one year.
 */
@Entity
@Table(name = "stock_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockPriceHistory implements Serializable {

    @EmbeddedId
    private StockPriceHistoryId id;

    @MapsId(value = "ticker")
    @ManyToOne(fetch = FetchType.LAZY)
    private Stock stock;

    @Column(name = "price")
    private Double stockPrice;
}
