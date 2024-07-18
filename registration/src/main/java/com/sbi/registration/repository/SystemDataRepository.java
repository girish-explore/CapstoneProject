package com.sbi.registration.repository;

import com.sbi.registration.entity.SystemData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemDataRepository extends JpaRepository<SystemData, String> {
}
