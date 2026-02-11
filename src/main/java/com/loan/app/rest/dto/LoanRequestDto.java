package com.loan.app.rest.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoanRequestDto {
    @NotNull
    @NotEmpty
    private String LoanTakenFrom;
    @Min(30000)
    private Double amount;
    private Double profitRate;

    @NotNull(message = "Duration in months is required.")
    private Integer durationInMonths;
}
