package com.sbi.registration.controller;

import com.sbi.registration.entity.SystemData;
import com.sbi.registration.service.SystemDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SystemDataController {
    @Autowired
    private SystemDataService systemDataService;
    @PostMapping("/new-customer-info")
    ResponseEntity<SystemData> basicInfoOfNewCustomer(@RequestBody SystemData newCustomer){
        return new ResponseEntity<>(systemDataService.newCustomer(newCustomer), HttpStatus.OK);
    }
}
