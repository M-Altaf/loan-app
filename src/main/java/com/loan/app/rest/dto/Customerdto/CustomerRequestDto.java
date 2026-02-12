package com.loan.app.rest.dto.Customerdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class CustomerRequestDto {
    @NotEmpty
    private String fullName;
    @Email
    private String email;
    @NotNull
    private String phoneNumber;
    @NotEmpty
    private String address;
    @Min(20000)
    private Double monthlyIncome;

}

