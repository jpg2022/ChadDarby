package com.luv2code.springcoredemo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.module.Configuration;

@Component//mark as spring bean
public class CricketCoach implements Coach{

    @Override
    public String getDailyWorkout(){
        return "Practice fast bowling for 15 minutes???";
    }

    @PostConstruct
    public void doMyStartupStuff(){
        System.out.println("In do my startup stuff method: " + getClass().getSimpleName());
    }

    @PreDestroy
    public void doMyCleanupStuff(){
        System.out.println("In do my cleanup stuff method: " + getClass().getSimpleName());
    }


}
