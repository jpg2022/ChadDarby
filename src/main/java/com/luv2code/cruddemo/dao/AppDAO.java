package com.luv2code.cruddemo.dao;


import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.stereotype.Component;

public interface AppDAO {
    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

//    InstructorDetail findInstructorDetailById(int theId);
}
