package com.sbi.registration.service;

import com.sbi.registration.entity.SystemData;
import com.sbi.registration.repository.SystemDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemDataServiceImpl implements SystemDataService{
    @Autowired
    private SystemDataRepository systemDataRepository;
    @Override
    public SystemData newCustomer(SystemData newCustomer) {
        return systemDataRepository.save(newCustomer);
    }
}
