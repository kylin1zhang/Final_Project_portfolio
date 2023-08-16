package org.example.stockmarket.cash.service;

import org.example.stockmarket.cash.repository.CashRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CashService {

    @Autowired
    CashRepository cashRepository;
}
