package com.example.springbootdruid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { JpaRepositoriesAutoConfiguration.class })//禁止springboot自动加载持久化bean
public class SpringbootDruidApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDruidApplication.class, args);
    }

}
