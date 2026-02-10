package com.loan.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
   @Entity
   @Data
    public class Loan{
    @Id
    @GeneratedValue
    private long id;
    private String ConsumerName;
    private double amount;

}
