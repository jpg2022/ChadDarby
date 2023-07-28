package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.dao.StudentDAOImpl;
import com.luv2code.cruddemo.entity.Student;
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
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
        return runner ->{
            //createStudent(studentDAO);
            createMultipleStudent(studentDAO);
//            readStudent(studentDAO);

//            queryForStudents(studentDAO);
//            queryForStudentsByLastName(studentDAO);
//            updateStudent(studentDAO);
//            deleteStudent(studentDAO);
//            deleteAllStudents(studentDAO);
        };
    }

    private void deleteAllStudents(StudentDAO studentDAO) {
        System.out.println("Deleting all students");
        int numrowsdeleted = studentDAO.deleteAll();
        System.out.println("Deleted " + numrowsdeleted + " rows");
    }

    private void deleteStudent(StudentDAO studentDAO) {
        int studentId=3;
        System.out.println("Deleting student");
        studentDAO.delete(studentId);
    }

    private void updateStudent(StudentDAO studentDAO) {
        int studentId=1;
        Student myStudent = studentDAO.findById(studentId);
        myStudent.setFirstName("Scooby");
        studentDAO.update(myStudent);
        System.out.println(myStudent);
    }

    private void queryForStudentsByLastName(StudentDAO studentDAO) {
        List<Student> theStudents = studentDAO.findByLastName("Doe");
        for(Student student:theStudents){
            System.out.println(student);
        }
    }

    private void queryForStudents(StudentDAO studentDAO){
        List<Student> theStudents = studentDAO.findAll();

        for(Student student:theStudents){
            System.out.println(student);
        }
    }

    private void readStudent(StudentDAO studentDAO){
        System.out.println(studentDAO.findById(2));
    }

    private void createMultipleStudent(StudentDAO studentDAO) {
        System.out.println("Creating student object...");
        Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
        Student tempStudent2 = new Student("Mary", "Doe", "mary@luv2code.com");
        Student tempStudent3 = new Student("Bonita", "Doe", "bonita@luv2code.com");

        System.out.println("Saving student objects");
        studentDAO.save(tempStudent1);
        studentDAO.save(tempStudent2);
        studentDAO.save(tempStudent3);
    }

    private void createStudent(StudentDAO studentDAO) {
        //create student object
        System.out.println("Creating student object...");
        Student tempStudent = new Student("Paul", "Doe", "paul@luv2code.com");
        //save the object
        System.out.println("Saving student object");
        studentDAO.save(tempStudent);

        //display id of saved student
        System.out.println("Saved Student. Generated id: " + tempStudent.getId());
    }

}
