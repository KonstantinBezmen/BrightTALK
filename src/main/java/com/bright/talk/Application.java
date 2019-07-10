package com.bright.talk;

import com.bright.talk.infrastructure.db.MigrationHelper;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        context.getBean(MigrationHelper.class).migrate();

    }

    @Bean
    public Mapper getMapper(){
        return new DozerBeanMapper();
    }
}