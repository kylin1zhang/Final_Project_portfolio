package org.example.stockmarket.market.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.example.stockmarket.market.enums.MarketTrajectory;

import java.time.ZonedDateTime;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Market {

    @Id
    @JsonIgnore
    private final Integer id = 1;

    
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime date;
    
    @Column
    private Double lastMonthAveragePrice;

    @Column
    @Enumerated(EnumType.STRING)
    private MarketTrajectory marketTrajectory;

    public void increment() {
        ZonedDateTime newDate = getDate().plusHours(1);
        setDate(newDate);
    }
}
