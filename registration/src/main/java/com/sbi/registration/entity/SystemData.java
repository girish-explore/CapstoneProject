package com.sbi.registration.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.ThreadLocalRandom;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class  SystemData {
    @Id
    private String accountNumber= generateAccountNumber();
    private String firstName;
    private String lastName;
    @Email
    private String email;
    static String generateAccountNumber(){
        int randomNumber = ThreadLocalRandom.current().nextInt(100000,1000000);
        return "SBI00"+randomNumber;
    }

}
