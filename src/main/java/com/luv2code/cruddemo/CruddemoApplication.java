package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO){
        return  runner->{

//        createCourseAndReviews(appDAO);
//            retrieveCourseAndReviews(appDAO);
            deleteCourseAndReviews(appDAO);

     };
    }

    private void deleteCourseAndReviews(AppDAO appDAO) {
        appDAO.deleteCourseById(10);
    }

    private void retrieveCourseAndReviews(AppDAO appDAO) {
        int theId= 10;
        Course tempCourse = appDAO.findCourseAndReviewsById(theId);
        System.out.println(tempCourse);
        System.out.println(tempCourse.getReviews());
    }

    private void createCourseAndReviews(AppDAO appDAO) {
        Course tempCourse = new Course("Pacman");
        tempCourse.addReview(new Review("Great course!"));
        tempCourse.addReview(new Review("Cool course!"));
        tempCourse.addReview(new Review("Sweet course!"));
        appDAO.save(tempCourse);
    }

    private void deleteCourse(AppDAO appDAO) {
        int theId=10;
        appDAO.deleteCourseById(theId);
    }

    private void updateCourse(AppDAO appDAO) {
        int theId =10;
        Course tempCourse = appDAO.findCourseById(theId);
        tempCourse.setTitle("Algebra");
        appDAO.update(tempCourse);
    }

    private void updateInstructor(AppDAO appDAO) {
        int theId=1;

        Instructor tempInstructor=  appDAO.findInstructorById(theId);
        System.out.println(tempInstructor);
        tempInstructor.setLastName("Tester");
        System.out.println("Updating instructor to " + tempInstructor);
        appDAO.update(tempInstructor);
    }

    private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
        int theId=1;

        Instructor tempInstructor=  appDAO.findInstructorByIdJoinFetch(theId);
        System.out.println(tempInstructor);
        System.out.println(tempInstructor.getCourses());


    }

    private void findCoursesForInstructor(AppDAO appDAO) {
        int theId = 1;
        Instructor tempInstructor = appDAO.findInstructorById(theId);

        System.out.println("tempInstructor: " + tempInstructor);
        List<Course> courses= appDAO.findCoursesByInstructorId(theId);
        tempInstructor.setCourse(courses);
        System.out.println("Found associated courses: " + tempInstructor.getCourses());

    }

    private void findInstructorWithCourses(AppDAO appDAO) {
        int theId = 1;
        Instructor tempInstructor = appDAO.findInstructorById(theId);

        System.out.println("tempInstructor: " + tempInstructor);
        System.out.println("associate courses" + tempInstructor.getCourses());
    }

    private void createInstructorWithCourses(AppDAO appDAO) {
        Instructor tempInstructor = new Instructor("Susan", "Public","madhu@luv2code.com");
        InstructorDetail tempInstructorDetail = new InstructorDetail("https://www.youtube.com/@luv2codetv", "Games");
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        Course tempCourse1 = new Course("Air guitar class");
        Course tempCourse2 = new Course("Pinball class");
        tempInstructor.add(tempCourse1);
        tempInstructor.add(tempCourse2);

        System.out.println("Saving instructor: " + tempInstructor +"\n And courses:" + tempCourse1 + tempCourse2);
        appDAO.save(tempInstructor);//this will also create a new instructor detail in the db bc of cascading
    }

    private void deleteInstructorDetail(AppDAO appDAO) {
        int theId=3;
        appDAO.deleteInstructorDetailById(theId);
        System.out.println("Deleted instructor detail by id: " + theId);
    }

    private void findInstructorDetail(AppDAO appDAO) {
        int theId =2;
        InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);
        System.out.println("Found instructor details: " + tempInstructorDetail);
        System.out.println("Associated instructor:" + tempInstructorDetail.getInstructor());
    }

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
