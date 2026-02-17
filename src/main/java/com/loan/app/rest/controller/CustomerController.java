package com.loan.app.rest.controller;

import com.loan.app.rest.dto.Customerdto.CustomerRequestDto;
import com.loan.app.rest.dto.Customerdto.CustomerResponseDto;
import com.loan.app.service.impl.CustomerServiceServiceImpl;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customers")
public class CustomerController {

    private final CustomerServiceServiceImpl customerService;

    public CustomerController(CustomerServiceServiceImpl customerService){
        this.customerService = customerService;
    }
    @PostMapping("/create")
    public CustomerResponseDto createCustomer(@RequestBody @Valid CustomerRequestDto customerRequestDto){
        return customerService.addCustomer(customerRequestDto);

    }
    @GetMapping("/get")
public List<CustomerResponseDto> getAllCustomer(){
        return customerService.getAllCustomer();
    }

    //get by id
    //delete by id
    //put by id
    //patch by id
}
