package org.example.transaction.model.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockTransactionDTO {
    private String stockName;  // 新增字段
    private Integer amount;
    private LocalDateTime timestamp;
    private String buySell;
    private Double price;
}