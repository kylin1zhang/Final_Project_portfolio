package org.example.stockmarket.stock.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class StockPriceHistoryId implements Serializable {

    
    @Column(name = "market_date")
    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime  marketDate;

    @Column(name = "ticker")
    private String ticker;
}
