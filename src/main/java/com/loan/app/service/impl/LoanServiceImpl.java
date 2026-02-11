package com.loan.app.service.impl;

import com.loan.app.entity.LoanEntity;
import com.loan.app.repository.LoanRepository;
import com.loan.app.rest.dto.LoanRequestDto;
import com.loan.app.rest.dto.LoanResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;

    public LoanServiceImpl(LoanRepository loanRepository){
        this.loanRepository = loanRepository;
    }


    @Override
    public LoanResponseDto addLoan(LoanRequestDto loanRequestDto){

        // Convert RequestDTO → Entity
        LoanEntity loan = new LoanEntity();
        loan.setConsumerName(loanRequestDto.getConsumerName());
        loan.setAmount(loanRequestDto.getAmount());

        // Save to database
        LoanEntity savedLoan = loanRepository.save(loan);

        // Convert Entity → ResponseDTO
        return new LoanResponseDto(
                savedLoan.getId(),
                savedLoan.getConsumerName(),
                savedLoan.getAmount()
        );
    }

    @Override
    public List<LoanResponseDto> getAllLoans() {

        return loanRepository.findAll()
                .stream()
                .map(loan -> new LoanResponseDto(
                        loan.getId(),
                        loan.getConsumerName(),
                        loan.getAmount()
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
        existingLoan.setConsumerName(loanRequestDto.getConsumerName());
        existingLoan.setAmount(loanRequestDto.getAmount());

        LoanEntity updatedLoan = loanRepository.save(existingLoan);

        return new LoanResponseDto(
                updatedLoan.getId(),
                updatedLoan.getConsumerName(),
                updatedLoan.getAmount()
        );
    }

}

