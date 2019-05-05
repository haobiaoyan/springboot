package com.example.springbootdruid.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DbConfig {

    @Bean(name = "primary")
    @ConfigurationProperties("spring.datasource.druid.primary")
    public DataSource primaryDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "secondary")
    @ConfigurationProperties("spring.datasource.druid.secondary")
    public DataSource secondaryDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

}
