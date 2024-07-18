package com.sbi.registration.service;

import com.sbi.registration.dto.CustomerRegistrationDto;
import com.sbi.registration.entity.Customer;

import java.util.List;

public interface CustomerService {
    //register customer
    Customer saveCustomer(CustomerRegistrationDto customerDto);
    String customerLogin(String username, String password);
}
