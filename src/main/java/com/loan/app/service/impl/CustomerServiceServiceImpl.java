package com.loan.app.service.impl;

import com.loan.app.entity.CustomerEntity;
import com.loan.app.repository.CustomerRepository;
import com.loan.app.rest.dto.Customerdto.CustomerRequestDto;
import com.loan.app.rest.dto.Customerdto.CustomerResponseDto;
import com.loan.app.service.impl.Interface.CustomerService;
import org.hibernate.boot.internal.Abstract;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;


    public CustomerServiceServiceImpl(CustomerRepository customer){
        this.customerRepository = customer;

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


}
