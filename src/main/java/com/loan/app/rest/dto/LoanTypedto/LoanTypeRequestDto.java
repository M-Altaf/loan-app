package com.loan.app.rest.dto.LoanTypedto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoanTypeRequestDto {
    @NotEmpty
    private String name;
    @Valid
    private String description;
    @Max(50000)
    private Double maxAmount;
    @Positive
    private Double baseInterestRate;
}
