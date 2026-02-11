package com.loan.app.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class LoanType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;          // Home Loan, Car Loan, Personal Loan
    private String description;
    private Double maxAmount;
    private Double baseInterestRate;
}
