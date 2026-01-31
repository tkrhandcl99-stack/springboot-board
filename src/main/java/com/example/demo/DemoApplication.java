package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner seed(PostRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                repo.save(new Post("첫 글", "DB에 저장된 첫 번째 글입니다."));
                repo.save(new Post("둘째 글", "AJAX로 불러올 예정!"));
            }
        };
    }
}