package com.lawless;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class Authorization {


    public static void main(String[] args) {
        SpringApplication.run(Authorization.class, args);
    }
}
