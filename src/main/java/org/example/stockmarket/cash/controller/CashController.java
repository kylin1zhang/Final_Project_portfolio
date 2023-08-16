package org.example.stockmarket.cash.controller;

import org.example.stockmarket.cash.service.CashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/cash")
public class CashController {

    @Autowired
    CashService cashService;


}
