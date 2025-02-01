package com.habitlegends.habitlegends.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.habitlegends.habitlegends.entity.Rank;
import com.habitlegends.habitlegends.repository.RankRepository;

@Configuration
public class RankDataInitializer {

    @Bean
    public CommandLineRunner initializeRanks(RankRepository rankRepository) {
        return args -> {
            // Check if ranks already exist to avoid duplicates
            if (rankRepository.count() == 0) {
                rankRepository.save(new Rank(null, "E", 0));
                rankRepository.save(new Rank(null, "D", 100));
                rankRepository.save(new Rank(null, "C", 300));
                rankRepository.save(new Rank(null, "B", 600));
                rankRepository.save(new Rank(null, "A", 1000));
                rankRepository.save(new Rank(null, "S", 1500));
            }
        };
    }
}
