package com.loan.app.rest.dto;

public class LoanResponseDto {
    private Long id;
    private String ConsumerName;
    private double amount;
    public LoanResponseDto(Long id, String ConsumerName, double amount){
        this.id = id;
        this.ConsumerName = ConsumerName;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public String getConsumerName() {
        return ConsumerName;
    }

    public double getAmount() {
        return amount;
    }
}
