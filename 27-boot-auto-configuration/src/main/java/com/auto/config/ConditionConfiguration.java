package com.auto.config;

import com.auto.importselect.GpCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConditionConfiguration {

    @Bean
    @Conditional(GpCondition.class)
    public Person getPerson(){
        return new Person();
    }
}
