package com.sbi.registration.service;

import com.sbi.registration.dto.CustomerRegistrationDto;
import com.sbi.registration.entity.Customer;
import com.sbi.registration.exception.InvalidInputException;
import com.sbi.registration.exception.OperationFailedException;
import com.sbi.registration.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private final CustomerRepository customerRepository;
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }
    @Override
    public Customer saveCustomer(CustomerRegistrationDto customerDto) {
        //Check all the fields are present
        if (customerDto.getFirstname()==null||customerDto.getLastname()==null||customerDto.getEmail()==null||
        customerDto.getEmail()==null||customerDto.getPhone()==null||customerDto.getUsername()==null||customerDto.getPassword()==null)
            throw new InvalidInputException("All Fields must be Mandatory to fill");

        // Check the customer already exists or not
        Customer customerFound= customerRepository.findByFirstnameAndLastnameAndPhone(customerDto.getFirstname(),
                customerDto.getLastname(),customerDto.getPhone());
        if (customerFound!=null){
            if(!customerFound.getCustomerId().isEmpty()) throw new OperationFailedException("Online Banking already exist for this customer");
        }

        // Create unique customer id
        String customerId= customerIdGenerator();
        while (customerRepository.existsById(customerId)){
            customerId=customerIdGenerator();
        }
        //Registering new customer
        return customerRepository.save(new Customer(customerId, customerDto.getFirstname(), customerDto.getLastname(),
                customerDto.getEmail(), customerDto.getPhone(), customerDto.getUsername(), customerDto.getPassword()));
    }
    public String customerIdGenerator(){
        String temp= "SBIN07"+String.valueOf((int)(Math.random() * 900000) + 100000);
        return temp;
    }

    @Override
    public String customerLogin(String username, String password) {
        logger.info("Attempting login for username: {}", username);
        if (username==null||password==null) throw new InvalidInputException("Username and password must not be null");
        Customer findCustomer= customerRepository.findByUsernameAndPassword(username, password);
        return (findCustomer!=null)?"Login successful":"username or password are wrong";


    }

}
