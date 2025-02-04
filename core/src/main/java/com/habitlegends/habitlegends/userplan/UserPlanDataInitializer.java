package com.habitlegends.habitlegends.userplan;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserPlanDataInitializer {
    @Bean
    public CommandLineRunner initializePlans(UserPlanRepository userPlanRepository) {
        return args -> {
            if (userPlanRepository.count() == 0) {
                userPlanRepository.save(new UserPlan(
                        null,
                        "Free",
                        "Free plan",
                        new BigDecimal(1.0),
                        new BigDecimal(1.0),
                        new BigDecimal(0.00),
                        null,
                        null));

            }
        };
    }
}