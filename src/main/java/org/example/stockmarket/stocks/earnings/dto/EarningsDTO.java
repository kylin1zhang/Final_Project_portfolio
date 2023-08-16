package org.example.stockmarket.stocks.earnings.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.stockmarket.stocks.earnings.entity.EarningsReport;

import java.time.ZonedDateTime;

@Getter
@Setter
public class EarningsDTO {

    private double estimatedEPS;
    private double actualEPS;
    private String reportMessage;
    private ZonedDateTime dateOfRelease;

    public EarningsDTO(EarningsReport earnings){
        this.estimatedEPS = earnings.getEstimatedEPS();
        this.actualEPS = earnings.getActualEPS();
        this.reportMessage = earnings.getReportMessage();
        this.dateOfRelease = earnings.getDateOfRelease();
    }
}
