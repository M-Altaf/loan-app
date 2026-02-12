package com.loan.app.rest.controller;

import com.loan.app.rest.dto.Customerdto.CustomerRequestDto;
import com.loan.app.rest.dto.Customerdto.CustomerResponseDto;
import com.loan.app.service.impl.CustomerServiceServiceImpl;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer/api")
public class CustomerController {

    private final CustomerServiceServiceImpl customerService;

    public CustomerController(CustomerServiceServiceImpl customerService){
        this.customerService = customerService;
    }
    @PostMapping("/create/api")
    public CustomerResponseDto createCustomer(@RequestBody @Valid CustomerRequestDto customerRequestDto){
        return customerService.addCustomer(customerRequestDto);

    }
    @GetMapping("/get/customer")
public List<CustomerResponseDto> getAllCustomer(){
        return customerService.getAllCustomer();
    }
}
