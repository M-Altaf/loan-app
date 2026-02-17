package com.loan.app.rest.controller;

import com.loan.app.rest.dto.Loandto.LoanRequestDto;
import com.loan.app.rest.dto.Loandto.LoanResponseDto;
import com.loan.app.service.impl.LoanServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/loan")
public class LoanController {

    private final LoanServiceImpl loanService;

public LoanController(LoanServiceImpl loanService) {
        this.loanService = loanService;
    }

@PostMapping("/create")
public LoanResponseDto createLoan(@RequestBody @Valid LoanRequestDto loanRequestDto) {
        return loanService.addLoan(loanRequestDto);
    }

@GetMapping("/get")
public List<LoanResponseDto> getAllLoans(){
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

