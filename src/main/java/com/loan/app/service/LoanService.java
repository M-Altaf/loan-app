package com.loan.app.service;

import com.loan.app.entity.Loan;
import com.loan.app.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LoanService {

     LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository){
        this.loanRepository =loanRepository;
    }
    public Loan addLoan(Loan loan){
        return loanRepository.save(loan);
    }
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

}
