package com.sbi.registration.controller;

import com.sbi.registration.dto.CustomerRegistrationDto;
import com.sbi.registration.dto.LoginDto;
import com.sbi.registration.entity.Customer;
import com.sbi.registration.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/customer")
public class CustomerController {
    private static final Logger logger= LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    private CustomerService customerService;
    @PostMapping("/registration")
    public ResponseEntity<?> customerRegistration(@RequestBody CustomerRegistrationDto customerDto){
        return new ResponseEntity<>(customerService.saveCustomer(customerDto), HttpStatus.CREATED);
    }
    @GetMapping("/login")
    public ResponseEntity<?> customerLogin(@RequestBody LoginDto loginDto) {
        logger.info("Login request received for username: "+ loginDto.getUsername());
        return new ResponseEntity<>(customerService.customerLogin(loginDto.getUsername(), loginDto.getPassword()),HttpStatus.OK);
    }
}
