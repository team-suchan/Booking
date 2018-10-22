package com.newboynb.bookingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class BookingsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookingsystemApplication.class, args);
    }
}
