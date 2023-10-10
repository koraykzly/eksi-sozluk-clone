package com.example.eksi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class EksiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EksiApplication.class, args);
    }

    @SuppressWarnings("unused")
    private void getBeanList(ConfigurableApplicationContext context) {
        String[] beanNames = context.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }
}
