package com.sbi.registration.utility;

import java.util.concurrent.ThreadLocalRandom;

public class OneTimePassword {
    public int generateOtp(){
        int randomNumber = ThreadLocalRandom.current().nextInt(100000,1000000);
        System.out.println("OTP is: "+ randomNumber);
        return  randomNumber;
    }
}
