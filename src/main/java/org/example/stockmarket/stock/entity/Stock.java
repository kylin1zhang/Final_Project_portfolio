package org.example.stockmarket.stock.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "stock")
@Table(name = "stock")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
/*
TODO:
    - refactor stock price history (called StockHistory now) to be included in the stock instead of
        a separate entity that must be queried for separately, will be OneToMany relationship
 */
public class Stock{

    @Id
    private String ticker;

    @Column(name = "name")
    private String companyName;

    @Column(name = "sector")
    private String sector;

    @Column(name = "cap")
    private String marketCap;

    @Column(name = "price")
    private Double price;

    @Column(name = "last_day_price")
    private Double lastDayPrice;

    @Column(name = "momentum")
    private Integer momentum;

    @Column(name = "momentum_streak")
    private Integer momentumStreakInDays;

    @Column(name = "volatile")
    private String volatileStock;

    @Column(name = "investor_rating")
    private String investorRating;

    public void updateMomentum() {
        int momentumStreak = getMomentumStreakInDays();
        if (momentumStreak >= 3) {
            setMomentum(1);
            return;
        }
        if (momentumStreak <= -3) {
            setMomentum(-1);
            return;
        }
        setMomentum(0);
    }

    public void updateMomentumStreak() {
        if (getMomentumStreakInDays() == null) {
            setMomentumStreakInDays(0);
            return;
        }
        double price = getPrice();
        int momentumStreakDays = getMomentumStreakInDays();
        if (price > getLastDayPrice()) {
            if(momentumStreakDays <= -1){
                setMomentumStreakInDays(0);
                return;
            }
            setMomentumStreakInDays(momentumStreakDays + 1);
            return;
        }
        if (price < getLastDayPrice()) {
            if(getMomentum() >= 1){
                setMomentumStreakInDays(0);
                return;
            }
            setMomentumStreakInDays(momentumStreakDays - 1);
        }
    }

}