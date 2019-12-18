package com.lawless.restoperation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@Slf4j
@EnableEurekaClient
@SpringBootApplication
public class RestOperationApplication {

    public static void main(String[] args) {

        SpringApplication.run(RestOperationApplication.class, args);
        log.info("RestOperationApplication started");
    }

}
