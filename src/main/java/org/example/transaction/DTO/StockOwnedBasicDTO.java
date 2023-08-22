package org.example.transaction.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockOwnedBasicDTO {
    private String ticker;
    private Integer amountOwned;
    private Double costBasis;
}

