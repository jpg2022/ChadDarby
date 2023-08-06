package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public class AppDAOImpl implements AppDAO{

    private EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }
    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);//this will persist to instructor detail
    }

    @Override
    public Instructor findInstructorById(int theId) {
       return entityManager.find(Instructor.class, theId);//also retrieves details bc of eager loading and cascade all
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        Instructor tempInstructor = entityManager.find(Instructor.class,theId);
        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);//also returns associated instructor object due to one to one cascade in instructor detail
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        InstructorDetail tempInstrcutorDetail = entityManager.find(InstructorDetail.class, theId);
        //break bi direcntional link, bc we are deleting the instructor detail that is linked to the instructor, it should be null after
        tempInstrcutorDetail.getInstructor().setInstructorDetail(null);
        entityManager.remove(tempInstrcutorDetail);
    }
}
