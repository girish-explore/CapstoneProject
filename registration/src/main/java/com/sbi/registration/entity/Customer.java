package com.sbi.registration.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Customer {
    @Id
    private String customerId;
    private String firstName;
    private String lastName;
    @NaturalId(mutable = true)
    private String email;
    private int mobileNumber;
    @NaturalId
    private String username;
    private String password;
}
