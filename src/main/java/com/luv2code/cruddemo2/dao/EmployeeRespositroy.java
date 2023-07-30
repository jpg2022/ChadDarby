package com.luv2code.cruddemo2.dao;

import com.luv2code.cruddemo2.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRespositroy extends JpaRepository<Employee, Integer> {

}
