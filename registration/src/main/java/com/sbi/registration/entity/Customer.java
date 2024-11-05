package com.sbi.registration.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Customer {
    @Id
    private String customerId;
    private String firstname;
    private String lastname;
    private String email;
    private Integer phone;
    private String username;
    private String password;
}
