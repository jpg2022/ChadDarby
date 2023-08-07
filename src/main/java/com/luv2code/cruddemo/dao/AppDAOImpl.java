package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        List<Course> courses = tempInstructor.getCourses();
        for (Course course : courses){//remove instructor from course, or else it will cause exception
            course.setInstructor(null);
        }
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

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id = :data", Course.class);
        query.setParameter("data", theId);
        List<Course> courses = query.getResultList();
        return courses;


    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        TypedQuery<Instructor> query = entityManager.createQuery("select i from Instructor i " +
                        "JOIN FETCH i.courses " + "JOIN FETCH i.instructorDetail "+"where i.id = : data",
                Instructor.class);

        query.setParameter("data", theId);

        Instructor instructor = query.getSingleResult();

        return instructor;

    }

    @Override
    @Transactional
    public void update(Instructor tempInstructor) {
        entityManager.merge(tempInstructor);
    }

    @Override
    @Transactional
    public void update(Course course) {
        entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class,theId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        entityManager.remove(entityManager.find(Course.class, theId));
    }
}
