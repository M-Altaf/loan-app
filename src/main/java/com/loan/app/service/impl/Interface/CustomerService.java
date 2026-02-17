package com.loan.app.service.impl.Interface;

import com.loan.app.rest.dto.Customerdto.CustomerRequestDto;
import com.loan.app.rest.dto.Customerdto.CustomerResponseDto;
import com.loan.app.rest.dto.Loandto.LoanRequestDto;
import com.loan.app.rest.dto.Loandto.LoanResponseDto;

import java.util.List;

public interface CustomerService {
    CustomerResponseDto addCustomer(CustomerRequestDto requestDto);
    List<CustomerResponseDto> getAllCustomer();
    LoanResponseDto updateLoan(Long id, LoanRequestDto dto);
}
