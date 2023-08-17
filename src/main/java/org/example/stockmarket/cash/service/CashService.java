package org.example.stockmarket.cash.service;

import org.example.stockmarket.cash.entity.Cash;
import org.example.stockmarket.cash.repository.CashRepository;
import org.example.stockmarket.stocks.stock.entity.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CashService {

    @Autowired
    CashRepository cashRepository;

    public List<Cash> getAllCash() {
        return cashRepository.findAll();
    }

    public List<Cash> getAllCashWithChange(int id,int change){
        Cash cash = cashRepository.findById(id).get();
        cash.setBalance(cashRepository.findById(id).get().getBalance()+change);
        cashRepository.save(cash);
        return cashRepository.findAll();
    }

    public void createCashAccount(String name,int change){
        if(!cashRepository.findByName(name).isEmpty()){
            throw new NullPointerException("Cannot Create Account With existing Username");
        }
        Cash cash = new Cash();
        cash.setId((int) (cashRepository.count()+1));
        cash.setCreated(new Date());
        cash.setModified(new Date());
        cash.setName(name);
        cash.setBalance(change);
        cashRepository.save(cash);
    }
}
