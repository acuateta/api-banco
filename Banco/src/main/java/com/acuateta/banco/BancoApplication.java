package com.acuateta.banco;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BancoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BancoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
