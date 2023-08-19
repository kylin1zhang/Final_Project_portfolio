package org.example.transaction.model.payload;

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

