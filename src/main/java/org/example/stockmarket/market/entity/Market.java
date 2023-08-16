package org.example.stockmarket.market.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
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
    private String marketTrajectory;

    public void increment() {
        ZonedDateTime newDate = getDate().plusHours(1);
        setDate(newDate);
    }
}
