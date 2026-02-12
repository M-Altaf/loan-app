package com.loan.app.rest.dto.Loandto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoanRequestDto {
    @NotNull
    @NotEmpty
    private String LoanTakenFrom;
    @Min(1000)
    @Max(80000)
    private Double amount;
    private Double profitRate;
    @NotNull(message = "Duration in months is required.")
    @Min(1)
    @Max(12)
    private Integer durationInMonths;
}
