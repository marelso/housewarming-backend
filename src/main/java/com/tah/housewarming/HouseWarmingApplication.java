package com.tah.housewarming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HouseWarmingApplication {

    public static void main(String[] args) {
        SpringApplication.run(HouseWarmingApplication.class, args);
    }

}
