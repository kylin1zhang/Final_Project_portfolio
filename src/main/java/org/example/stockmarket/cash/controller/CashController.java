package org.example.stockmarket.cash.controller;

import org.example.stockmarket.cash.entity.Cash;
import org.example.stockmarket.cash.entity.CashModified;
import org.example.stockmarket.cash.repository.CashRepository;
import org.example.stockmarket.cash.service.CashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://127.0.0.1:5501", "http://localhost:8080", "http://localhost:3000"})
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
                                           @RequestParam("change") float change){
        return cashService.getAllCashWithChange(id,change);
    }

    @PostMapping("/create")
    public Cash createCashAccount(@RequestParam("name") String name,
                                  @RequestParam("change") float change){
        cashService.createCashAccount(name,change);
        return cashService.getCashByUsername(name);
    }

    @DeleteMapping("/delete")
    public String createCashAccount(@RequestParam("name") String name){
        cashService.deleteUser(name);
        return name;
    }

    @GetMapping("/cashModified")
    public List<CashModified> getAllCashModified() {
        return cashService.getAllCashModified();
    }

    @PostMapping("/total")
    public List<List<CashModified>> cashModified(@RequestParam("name") String name,
                                                 @RequestParam("change") float balance){
        cashService.createCashModified(name,new Date(),balance);
        return cashService.getTotalCashWithChange(name,balance);
    }
}
