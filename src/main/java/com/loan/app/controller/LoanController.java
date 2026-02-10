package com.loan.app.controller;

import com.loan.app.entity.Loan;
import com.loan.app.service.LoanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Api/loan")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/create")
    public Loan createLoan(@RequestBody Loan loan) {
        return loanService.addLoan(loan);
    }
    @GetMapping("/get")
    public List<Loan> getAllLoans(){
        return loanService.getAllLoans();
    }

}
