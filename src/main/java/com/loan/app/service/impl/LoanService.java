package com.loan.app.service.impl;

import com.loan.app.rest.dto.LoanRequestDto;
import com.loan.app.rest.dto.LoanResponseDto;

import java.util.List;

public interface LoanService {
     LoanResponseDto addLoan(LoanRequestDto loanRequestDto);

     List<LoanResponseDto> getAllLoans();

    public void deleteLoan(Long id);

     LoanResponseDto updateLoan(Long id, LoanRequestDto loanRequestDto);
}
