package com.sbi.registration.repository;

import com.sbi.registration.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {
    Customer findByFirstnameAndLastnameAndPhone(String firstname, String lastname, Integer phone);
    Customer findByUsernameAndPassword(String username, String password);
}