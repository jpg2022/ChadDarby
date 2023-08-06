package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO){
        return  runner->{
//            createInstructor(appDAO);
//            findInstructor(appDAO);
//            deleteInstructor(appDAO);
//            findInstructorDetail(appDAO);
        };
    }

//    private void findInstructorDetail(AppDAO appDAO) {
//        int theId =1;
//        InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);
//        System.out.println("Found instructor details: " + tempInstructorDetail);
//        System.out.println("Associated instructor:" + tempInstructorDetail.getInstructor());
//    }

    private void deleteInstructor(AppDAO appDAO) {
        int theId =1;
        System.out.println("Deleting instructor with id:" + theId);

        appDAO.deleteInstructorById(theId);
        System.out.println("Done!");//
    }

    private void findInstructor(AppDAO appDAO){
        int theId= 2;
        System.out.println("Finding instructor with id: " +theId);
        Instructor tempInstructor=  appDAO.findInstructorById(theId);
        System.out.println("Found instructor: " + tempInstructor);
        System.out.println("Instructors details: " + tempInstructor.getInstructorDetail());
    }

    private void createInstructor(AppDAO appDAO) {
//        Instructor tempInstructor = new Instructor("Chad", "Darby","darby@luv2code.com");
//        InstructorDetail tempInstructorDetail = new InstructorDetail("https://www.youtube.com/@luv2codetv", "coding");
        Instructor tempInstructor = new Instructor("Madhu", "Patel","madhu@luv2code.com");
        InstructorDetail tempInstructorDetail = new InstructorDetail("https://www.youtube.com/@luv2codetv", "Guitar");
        tempInstructor.setInstructorDetail(tempInstructorDetail);
        System.out.println("SAving instructor: " + tempInstructor);
        appDAO.save(tempInstructor);//this will also create a new instructor detail in the db bc of cascading
    }

}
