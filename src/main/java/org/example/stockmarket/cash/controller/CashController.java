package org.example.stockmarket.cash.controller;

import org.example.stockmarket.cash.entity.Cash;
import org.example.stockmarket.cash.service.CashService;
import org.example.stockmarket.stocks.stock.entity.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/cash")
public class CashController {

    @Autowired
    CashService cashService;

    @GetMapping
    public List<Cash> getAllCash() {
        return cashService.getAllCash();
    }

    @PostMapping
    public List<Cash> getAllCashWithChange(@RequestParam("id") int id,
                                           @RequestParam("change") int change){
        return cashService.getAllCashWithChange(id,change);
    }

    @PostMapping("/create")
    public void createCashAccount(@RequestParam("name") String name,
                                  @RequestParam("change") int change){
        cashService.createCashAccount(name,change);
    }
}
