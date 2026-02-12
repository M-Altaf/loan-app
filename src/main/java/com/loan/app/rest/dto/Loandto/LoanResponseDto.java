package com.loan.app.rest.dto.Loandto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanResponseDto {
    private Long id;
    private String LoanTakenFrom;
    private Double amount;
    private Double profitRate;
    private Integer durationInMonths;
}
