package org.example.stockmarket.cash.service;

import org.example.stockmarket.cash.entity.Cash;
import org.example.stockmarket.cash.entity.CashModified;
import org.example.stockmarket.cash.repository.CashModifiedRepository;
import org.example.stockmarket.cash.repository.CashRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CashService {

    @Autowired
    CashRepository cashRepository;

    @Autowired
    CashModifiedRepository cashModifiedRepository;

    public List<Cash> getAllCash() {
        return cashRepository.findAll();
    }

    public Cash getCashByUsername(String username) {
        List<Cash> allCash = this.getAllCash();
        for (Cash cash: allCash) {
            if (cash.getName().equals(username)) {
                return cash;
            }
        }
        return null;
    }

    public List<Cash> getAllCashWithChange(int id,float change){
        Cash cash = cashRepository.findById(id).get();
        cash.setBalance(cashRepository.findById(id).get().getBalance()+change);
        cashRepository.save(cash);
        return cashRepository.findAll();
    }

    public void createCashAccount(String name,float change){
        if(!cashRepository.findByName(name).isEmpty()){
            throw new NullPointerException("Cannot Create Account With existing Username");
        }
        Cash cash = new Cash();
        cash.setCreated(new Date());
        cash.setModified(new Date());
        cash.setName(name);
        cash.setBalance(change);
        cashRepository.save(cash);
    }

    public boolean deleteUser(String name) {
        Cash lookupCash = this.getCashByUsername(name);
        if ( lookupCash != null) {
            int id = lookupCash.getId();
            cashRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void createCashModified(String name,Date modified,float balance){
        CashModified cashModified = new CashModified();
        cashModified.setName(name);
        cashModified.setModified(modified);
        cashModified.setBalance(balance);
        cashModifiedRepository.save(cashModified);
    }

    public List<CashModified> getAllCashModified() {
        return cashModifiedRepository.findAll();
    }

    public List<List<CashModified>> getTotalCashWithChange(String name,float change){
        Cash cash = new Cash();
        if(!cashRepository.findByName(name).isEmpty()){
            cash = this.getCashByUsername(name);
            cash.setBalance(cash.getBalance()+change);
        }else {
            cash.setName(name);
            cash.setCreated(new Date());
            cash.setModified(new Date());
            cash.setBalance(change);
        }
        cashRepository.save(cash);
        return returntotal(cashModifiedRepository.findAll());
    }

    public List<List<CashModified>> findHistory(){
        List<CashModified> cashModifiedList = cashModifiedRepository.findHistory(LocalDateTime.now().minusDays(7),new Date());
        System.out.println();
        return returntotal(cashModifiedList);
    }

    public List<List<CashModified>> returntotal(List<CashModified> allCash){
        List<CashModified> expendList = new ArrayList<>();
        List<CashModified> incomList = new ArrayList<>();
        List<List<CashModified>> cashList = new ArrayList<>();
        for (CashModified cashModified: allCash) {
            boolean flag = false;
            CashModified modified = new CashModified();
            if(cashModified.getBalance()>=0){
                for (int i=0;i<incomList.size();i++) {
                    if(cashModified.getName().equals(incomList.get(i).getName())){
                        modified.setName(cashModified.getName());
                        modified.setModified(cashModified.getModified());
                        modified.setBalance(incomList.get(i).getBalance()+cashModified.getBalance());
                        incomList.set(i,modified);
                        flag=true;
                    }
                }
                if(!flag){
                    modified.setName(cashModified.getName());
                    modified.setModified(cashModified.getModified());
                    modified.setBalance(cashModified.getBalance());
                    incomList.add(modified);
                }
            }else {
                for (int i=0;i<expendList.size();i++) {
                    if(cashModified.getName().equals(expendList.get(i).getName())){
                        modified.setName(cashModified.getName());
                        modified.setModified(cashModified.getModified());
                        modified.setBalance(Math.abs(expendList.get(i).getBalance()+cashModified.getBalance()));
                        expendList.set(i,modified);
                        flag=true;
                    }
                }
                if(!flag){
                    modified.setName(cashModified.getName());
                    modified.setModified(cashModified.getModified());
                    modified.setBalance(Math.abs(cashModified.getBalance()));
                    expendList.add(modified);
                }
            }
        }
        cashList.add(expendList);
        cashList.add(incomList);
        return cashList;
    }
}
