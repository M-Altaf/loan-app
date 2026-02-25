package com.loan.app.rest.dto.Loandto;


import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class LoanPatchRequestDto {
    private Long id;
    private String loanTakenFrom;
    private Double amount;
    private Double profitRate;
    private Integer durationInMonths;


}
