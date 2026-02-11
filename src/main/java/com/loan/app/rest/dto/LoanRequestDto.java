package com.loan.app.rest.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class LoanRequestDto {
@NotBlank
private String ConsumerName;
@Min(1000)
private double amount;

    public String getConsumerName() {
        return ConsumerName;
    }

    public void setConsumerName(String consumerName) {
       this.ConsumerName = consumerName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
