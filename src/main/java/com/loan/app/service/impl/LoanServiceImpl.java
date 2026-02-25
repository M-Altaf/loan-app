package com.loan.app.service.impl;

import com.loan.app.exception.ResourceNotFoundException.ResourceNotFoundException;
import com.loan.app.entity.LoanEntity;
import com.loan.app.repository.LoanRepository;
import com.loan.app.rest.dto.Loandto.LoanRequestDto;
import com.loan.app.rest.dto.Loandto.LoanResponseDto;
import com.loan.app.service.impl.Interface.LoanService;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LoanServiceImpl implements LoanService {

    private static final Logger log = LoggerFactory.getLogger(LoanServiceImpl.class);
    private final LoanRepository loanRepository;

public LoanServiceImpl(LoanRepository loanRepository){
        this.loanRepository = loanRepository;
    }


@Override
public LoanResponseDto addLoan(LoanRequestDto request){
        log.info("Adding loan payload: {}", request);
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
    @SneakyThrows
    public LoanResponseDto getLoanById(Long id) {
        LoanEntity loan = loanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found with id: " + id));

        return new LoanResponseDto(
                loan.getId(),
                loan.getLoanTakenFrom(),
                loan.getAmount(),
                loan.getProfitRate(),
                loan.getDurationInMonths()
        );
    }


@Override
public void deleteLoan(Long id) {

        LoanEntity loan = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found with id: " + id));

        loanRepository.delete(loan);
    }



    @Override
    public LoanResponseDto updateLoan(Long id, LoanRequestDto dto) {

        LoanEntity existingLoan = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found with id: " + id));

        // ✅ PUT = overwrite all fields

        existingLoan.setLoanTakenFrom(dto.getLoanTakenFrom());
        existingLoan.setAmount(dto.getAmount());
        existingLoan.setProfitRate(dto.getProfitRate());
        existingLoan.setDurationInMonths(dto.getDurationInMonths());

        LoanEntity updatedLoan = loanRepository.save(existingLoan);

        return new LoanResponseDto(
                updatedLoan.getId(),
                updatedLoan.getLoanTakenFrom(),
                updatedLoan.getAmount(),
                updatedLoan.getProfitRate(),
                updatedLoan.getDurationInMonths()
        );
    }

    @Override
    public LoanResponseDto patchLoan(Long id, LoanRequestDto dto) {

        LoanEntity existingLoan = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found with id: " + id));

        // ✅ update only provided fields

        if (dto.getLoanTakenFrom() != null) {
            existingLoan.setLoanTakenFrom(dto.getLoanTakenFrom());
        }

        if (dto.getAmount() != null) {
            existingLoan.setAmount(dto.getAmount());
        }

        if (dto.getProfitRate() != null) {
            existingLoan.setProfitRate(dto.getProfitRate());
        }

        if (dto.getDurationInMonths() != null) {
            existingLoan.setDurationInMonths(dto.getDurationInMonths());
        }

        LoanEntity updatedLoan = loanRepository.save(existingLoan);

        return new LoanResponseDto(
                updatedLoan.getId(),
                updatedLoan.getLoanTakenFrom(),
                updatedLoan.getAmount(),
                updatedLoan.getProfitRate(),
                updatedLoan.getDurationInMonths()
        );
    }



}

