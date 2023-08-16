package org.example.stockmarket.stocks.earnings.defaults;

import org.example.stockmarket.stocks.stock.entity.Stock;

import java.time.ZonedDateTime;

public class DefaultEarningsMessages {

    public static String getPositiveEarningsReport(Stock stock,
                                                   double estimatedEPS,
                                                   double actualEPS,
                                                   ZonedDateTime marketDate) {
        return "Date: " + marketDate + ". " + stock.getCompanyName() + " announces increased " +
                "profits in new earnings report today, causing a spike in their stock price." +
                " Their EPS of " + actualEPS + " exceeded expectations of " + estimatedEPS + " EPS.";
    }

    public static String getNeutralEarningsReport(Stock stock,
                                                  double estimatedEPS,
                                                  double actualEPS,
                                                  ZonedDateTime marketDate) {
        return "Date: " + marketDate + ". " + stock.getCompanyName() + " announces stable " +
                "profits in new earnings report today. Their EPS of " +
                actualEPS + " fell in line with expectations of " + estimatedEPS + " EPS.";
    }

    public static String getNegativeEarningsReport(Stock stock,
                                                   double estimatedEPS,
                                                   double actualEPS,
                                                   ZonedDateTime marketDate) {
        return "Date: " + marketDate + ". " + stock.getCompanyName() + " announces a slip in " +
                "profits in new earnings report today, causing a drop in their stock price." +
                " Their EPS of " + actualEPS + " came in lower than the expected " +
                estimatedEPS + " EPS.";
    }
}