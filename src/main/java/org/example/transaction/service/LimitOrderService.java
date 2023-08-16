package org.example.transaction.service;

import lombok.AllArgsConstructor;
import org.example.transaction.model.entity.Account;
import org.example.transaction.model.entity.LimitOrder;
import org.example.transaction.model.payload.BuyStockRequest;
import org.example.transaction.repository.LimitOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LimitOrderService {

    @Autowired
    private final LimitOrderRepository limitOrderRepository;
    @Autowired
    private final StockOwnedService stockOwnedService;

    public void saveLimitOrder(LimitOrder limitOrder) {
        limitOrderRepository.save(limitOrder);
    }

    public void deleteLimitOrder(LimitOrder limitOrder) {
        limitOrderRepository.delete(limitOrder);
    }

    public List<LimitOrder> findLimitOrdersByAccount(Account account) {
        return limitOrderRepository.findAll().stream()
                .filter(order -> order.getAccount().getUsername().equals(account.getUsername()))
                .collect(Collectors.toList());
    }

    public void processAllLimitOrders() {
        limitOrderRepository.findAll().forEach(order -> {
            if (order.getLimitPrice() < order.getStock().getPrice()) {
                try {
                    stockOwnedService.buyStock(new BuyStockRequest(
                            order.getAccount().getUsername(),
                            order.getStock().getTicker(),
                            order.getSharesToBuy()));
                    clearAndDeleteLimitOrder(order);
                } catch (javax.security.auth.login.AccountNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Transactional
    public void truncateLimitOrders() {
        limitOrderRepository.truncateTable();
    }

    private void clearAndDeleteLimitOrder(LimitOrder limitOrder) {
        limitOrder.setAccount(null);
        limitOrder.setStock(null);

        saveLimitOrder(limitOrder);
        deleteLimitOrder(limitOrder);
    }
}
