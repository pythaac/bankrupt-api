package com.bankrupt.bankruptapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class BankruptApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankruptApiApplication.class, args);
    }

}
