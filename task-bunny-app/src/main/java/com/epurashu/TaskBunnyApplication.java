package com.epurashu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.epurashu"})
@EntityScan(basePackages = {"com.epurashu"})
@ComponentScan(basePackages = {"com.epurashu"})
public class TaskBunnyApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(TaskBunnyApplication.class, args);
    }
}
