package com.sbi.registration.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRegistrationDto {
    private String firstname;
    private String lastname;
    private String email;
    private Integer phone;
    private String username;
    private String password;
}
