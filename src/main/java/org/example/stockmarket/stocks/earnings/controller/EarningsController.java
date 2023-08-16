package org.example.stockmarket.stocks.earnings.controller;

import lombok.AllArgsConstructor;
import org.example.stockmarket.stocks.earnings.entity.EarningsReport;
import org.example.stockmarket.stocks.earnings.service.EarningsService;
import org.example.stockmarket.stocks.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/earnings")
@AllArgsConstructor
public class EarningsController {

    @Autowired
    private final EarningsService earningsService;
    @Autowired
    private final StockService stockService;

    @GetMapping
    public List<EarningsReport> getAllEarningsReportHistory() {
        return earningsService.findAllEarningsReports();
    }


    //date is formatted as month_day_year here instead of month/day/year
    @RequestMapping(value = "/date/{date}")
    public List<EarningsReport> getEarningsOnDate(@PathVariable String date) {
        ZonedDateTime parsedDate = ZonedDateTime.parse(date);
        return earningsService.findAllEarningsByDate(parsedDate);
    }

}
