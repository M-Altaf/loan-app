package com.loan.app.service.impl;

import com.loan.app.entity.LoanEntity;
import com.loan.app.repository.LoanRepository;
import com.loan.app.rest.dto.Loandto.LoanRequestDto;
import com.loan.app.rest.dto.Loandto.LoanResponseDto;
import com.loan.app.service.impl.Interface.LoanService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;

public LoanServiceImpl(LoanRepository loanRepository){
        this.loanRepository = loanRepository;
    }


@Override
public LoanResponseDto addLoan(LoanRequestDto request){

        LoanEntity loan = new LoanEntity();
        loan.setAmount(request.getAmount());
        loan.setLoanTakenFrom(request.getLoanTakenFrom());
        loan.setProfitRate(request.getProfitRate());
        loan.setDurationInMonths(request.getDurationInMonths());

        // Save to database
        LoanEntity savedLoan = loanRepository.save(loan);

        // Convert Entity → ResponseDTO
        return new LoanResponseDto(
                savedLoan.getId(),
                savedLoan.getLoanTakenFrom(),
                savedLoan.getAmount(),
                savedLoan.getProfitRate(),
                savedLoan.getDurationInMonths()
        );
    }

@Override
public List<LoanResponseDto> getAllLoans() {

        return loanRepository.findAll()
               .stream()
               .map(loan -> new LoanResponseDto(
                       loan.getId(),
                       loan.getLoanTakenFrom(),
                       loan.getAmount(),
                       loan.getProfitRate(),
                       loan.getDurationInMonths()
        ))
               .toList();

    }

@Override
public void deleteLoan(Long id) {

        LoanEntity loan = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found with id: " + id));

        loanRepository.delete(loan);
    }
@Override
public LoanResponseDto updateLoan(Long id, LoanRequestDto loanRequestDto){

        LoanEntity existingLoan = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found with id: " + id));

        // Update fields
        existingLoan.setLoanTakenFrom(loanRequestDto.getLoanTakenFrom());
        existingLoan.setAmount(loanRequestDto.getAmount());
        existingLoan.setDurationInMonths(loanRequestDto.getDurationInMonths());
        existingLoan.setProfitRate(loanRequestDto.getProfitRate());

        LoanEntity updatedLoan = loanRepository.save(existingLoan);

        return new LoanResponseDto(
                updatedLoan.getId(),
                updatedLoan.getLoanTakenFrom(),
                updatedLoan.getAmount(),
                updatedLoan.getProfitRate(),
                updatedLoan.getDurationInMonths());

    }

}

