package com.loan.app.service.impl;

import com.loan.app.entity.CustomerEntity;
import com.loan.app.entity.LoanEntity;
import com.loan.app.repository.CustomerRepository;
import com.loan.app.repository.LoanRepository;
import com.loan.app.rest.dto.Customerdto.CustomerRequestDto;
import com.loan.app.rest.dto.Customerdto.CustomerResponseDto;
import com.loan.app.rest.dto.Loandto.LoanRequestDto;
import com.loan.app.rest.dto.Loandto.LoanResponseDto;
import com.loan.app.service.impl.Interface.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final LoanRepository loanRepository;


    public CustomerServiceImpl(CustomerRepository customer, LoanRepository loanRepository){
        this.customerRepository = customer;
        this.loanRepository = loanRepository;
    }
public CustomerResponseDto addCustomer(CustomerRequestDto requestDto){

    CustomerEntity customerEntity = new CustomerEntity();

    customerEntity.setFullName(requestDto.getFullName());
    customerEntity.setEmail(requestDto.getEmail());
    customerEntity.setPhoneNumber(requestDto.getPhoneNumber());
    customerEntity.setAddress(requestDto.getAddress());
    customerEntity.setMonthlyIncome(requestDto.getMonthlyIncome());

    CustomerEntity savedCustomer = customerRepository.save(customerEntity);

    return  new CustomerResponseDto(
            savedCustomer.getId(),
            savedCustomer.getFullName(),
            savedCustomer.getEmail(),
            savedCustomer.getPhoneNumber(),
            savedCustomer.getAddress(),
            savedCustomer.getMonthlyIncome()
    );

    }

    @Override
    public List<CustomerResponseDto> getAllCustomer() {

        return customerRepository.findAll()
                .stream()
                .map(customerEntity -> new CustomerResponseDto(
                        customerEntity.getId(),
                        customerEntity.getFullName(),
                        customerEntity.getEmail(),
                        customerEntity.getPhoneNumber(),
                        customerEntity.getAddress(),
                        customerEntity.getMonthlyIncome()

                ))
                .toList();
    }
    @Override
    public LoanResponseDto updateLoan(Long id, LoanRequestDto dto) {

        LoanEntity existingLoan = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found with id: " + id));

        // PUT = overwrite all fields

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


}
