package com.loan.app.rest.controller;

import com.loan.app.rest.dto.Loandto.LoanRequestDto;
import com.loan.app.rest.dto.Loandto.LoanResponseDto;
import com.loan.app.service.impl.LoanServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/loan")
public class LoanController {

    private static final Logger log = LoggerFactory.getLogger(LoanController.class);
    private final LoanServiceImpl loanService;

public LoanController(LoanServiceImpl loanService) {
        this.loanService = loanService;
    }

@PostMapping("/create")
public LoanResponseDto createLoan(@RequestBody @Valid LoanRequestDto loanRequestDto) {
    log.info("[createLoan request payload: {}", loanRequestDto);
        var response = loanService.addLoan(loanRequestDto);
        log.info("[createLoan response :{}",response);
        return response;
    }

@GetMapping("/get")
public List<LoanResponseDto> getAllLoans() {

        return loanService.getAllLoans();
    }


@GetMapping("/get/{id}")
public LoanResponseDto getLoanById
        (@PathVariable Long id){
    return loanService.getLoanById(id);
}

@DeleteMapping("/delete/{id}")
String deleteLoan(@PathVariable Long id){
        loanService.deleteLoan(id);
        return "Loan deleted successfully!";
    }

@PutMapping("/updata/{id}")
public LoanResponseDto patchLoan(@PathVariable Long id, @RequestBody LoanRequestDto patchDto){
    return loanService.patchLoan(id, patchDto);
}



@PatchMapping("/patch{id}")
public LoanResponseDto updateLoan(@PathVariable Long id, @RequestBody LoanRequestDto updateDto){
        return loanService.updateLoan(id, updateDto);
    }

}

