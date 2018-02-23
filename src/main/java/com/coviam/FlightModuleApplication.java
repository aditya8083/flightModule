package com.coviam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableEurekaClient
public class FlightModuleApplication {
    public static void main(String[] args) {
     SpringApplication.run(FlightModuleApplication.class, args);
    }

    @Bean
    public Logger getLogger()
    {
        return LogManager.getLogger(FlightModuleApplication.class.getPackage().getName());
    }


}