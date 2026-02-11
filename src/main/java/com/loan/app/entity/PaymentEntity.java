package com.loan.app.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long loanId;
    private Long customerId;
    private Long loanTypeId;

    private Double amountPaid;
    private LocalDate paymentDate;

    private String paymentStatus;   // PAID or UNPAID
    private String paymentMethod;
}

