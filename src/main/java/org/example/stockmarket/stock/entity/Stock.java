package org.example.stockmarket.stock.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.stockmarket.stock.defaults.DefaultStockPrices;
import org.example.stockmarket.stock.enums.InvestorRating;
import org.example.stockmarket.stock.enums.MarketCap;
import org.example.stockmarket.stock.enums.Volatility;

import java.util.List;

@Entity(name = "stock")
@Table(name = "stock")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor

/*
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

//    @Column(name = "cap")
//    private String marketCap;
    @Column(name = "cap")
    @Enumerated(EnumType.STRING)
    private MarketCap marketCap;

    @Column(name = "price")
    private Double price;

    @Column(name = "last_day_price")
    private Double lastDayPrice;

    @Column(name = "momentum")
    private Integer momentum;

    @Column(name = "momentum_streak")
    private Integer momentumStreakInDays;

    @Column(name = "volatile")
    @Enumerated(EnumType.STRING)
    private Volatility volatileStock;

    @Column(name = "investor_rating")
    @Enumerated(EnumType.STRING)
    private InvestorRating investorRating;

//    @Column(name = "volatile")
//    private String volatileStock;
//
//    @Column(name = "investor_rating")
//    private String investorRating;

    @OneToMany(mappedBy = "stock", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<StockPriceHistory> priceHistory;

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
    public Stock(String ticker,
                 String companyName,
                 String sector,
                 MarketCap marketCap,
                 Volatility volatileStock,
                 InvestorRating investorRating) {
        this.ticker = ticker;
        this.companyName = companyName;
        this.sector = sector;
        this.marketCap = marketCap;
        this.volatileStock = volatileStock;
        this.investorRating = investorRating;
        this.price = DefaultStockPrices.getDefaultPriceWithCap(marketCap);
        this.lastDayPrice = DefaultStockPrices.getDefaultPriceWithCap(marketCap);
        this.momentum = 0;
        this.momentumStreakInDays = 0;
    }

}