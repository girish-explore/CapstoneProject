package com.sbi.registration.service;

import com.sbi.registration.dto.CustomerRegistrationDto;
import com.sbi.registration.entity.Customer;
import com.sbi.registration.entity.SystemData;
import com.sbi.registration.exception.InvalidInputException;
import com.sbi.registration.exception.OperationFailedException;
import com.sbi.registration.exception.ResourceNotFoundException;
import com.sbi.registration.repository.CustomerRepository;
import com.sbi.registration.repository.SystemDataRepository;
import com.sbi.registration.utility.OneTimePassword;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private SystemDataRepository systemDataRepository;
    @Override
    public Customer saveCustomer(CustomerRegistrationDto customerDto) {
        Optional<Customer> customerExist= customerRepository.findById(customerDto.getCustomerId());
        // Checking customer with online banking credentials already exists
        if(!customerExist.isEmpty()) throw new OperationFailedException("Online Banking already exist for this customer");
        // retrieving system data given by customer ID
        Optional<SystemData> userDetails= systemDataRepository.findById(customerDto.getCustomerId());
        // if customer ID not exist in system data throw error
        if(userDetails.isEmpty()){
            throw new ResourceNotFoundException("Customer Details Not exists. Do KYC before Registering Online Banking");
        }
        if(userDetails.get().getFirstName().equals(customerDto.getFirstName()) && userDetails.get().getLastName().equals(customerDto.getLastName()) &&
                                                                                userDetails.get().getEmail().equals(customerDto.getEmail())){
            System.out.println("Enter One Time Password ");
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            OneTimePassword otp= new OneTimePassword();
            try {

                if(otp.generateOtp()== Integer.parseInt(input.readLine())){
                    Customer customer= new Customer(customerDto.getCustomerId(), customerDto.getFirstName(), customerDto.getLastName(),
                            customerDto.getEmail(),Integer.parseInt(customerDto.getMobileNumber()), customerDto.getUsername(), customerDto.getPassword());
                    System.out.println("Online Banking Registered Successful");
                    return customerRepository.save(customer);
                }else throw new InvalidInputException("Enter Valid OTP");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else throw new InvalidInputException("Enter the details associated with Account Number");
    }

    @Override
    public String customerLogin(String username, String password) {
        logger.info("Attempting login for username: {}", username);
        if (username==null||password==null) throw new InvalidInputException("Username and password must not be null");
        Customer findCustomer= customerRepository.findByUsername(username);
        if (findCustomer==null){
            throw new ResourceNotFoundException("username does not match with records");
        }
        if (findCustomer.getUsername().equals(username)){
            if (findCustomer.getPassword().equals(password)) return "Login Successful";
            else throw new OperationFailedException("Password Incorrect");
        }
        return null;
    }

}
