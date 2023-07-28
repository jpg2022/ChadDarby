package com.luv2code.springcoredemo.common;

import org.springframework.context.annotation.Configuration;


public class SwimCoach implements Coach{

    public SwimCoach(){
        System.out.println("In constructor: " + getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkout(){
        return "Swim 1000 meters for warm up";
    }
}
