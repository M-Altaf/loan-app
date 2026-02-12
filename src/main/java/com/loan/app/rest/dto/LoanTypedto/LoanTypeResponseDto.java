package com.loan.app.rest.dto.LoanTypedto;

import lombok.Data;

@Data
public class LoanTypeResponseDto {
    private Long id;
    private String name;          // Home Loan, Car Loan, Personal Loan
    private String description;
    private Double maxAmount;
    private Double baseInterestRate;
}
