package com.luv2code.cruddemo2.service;


import com.luv2code.cruddemo2.dao.EmployeeRespositroy;
import com.luv2code.cruddemo2.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//Service is for custom business logic, and for combining multiple daos
@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRespositroy employeeRespositroy;
    @Autowired
    public EmployeeServiceImpl(EmployeeRespositroy theEmployeeRepository){
        employeeRespositroy=theEmployeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRespositroy.findAll();
    }

    @Override
    public Employee findById(int theId) {
        Optional<Employee> result = employeeRespositroy.findById(theId);
        Employee theEmploye=null;
        if(result.isPresent()){
            theEmploye=result.get();
        }else{
            throw new RuntimeException("Did not find employee id");
        }
        return theEmploye;
    }

    @Override
    public Employee save(Employee theEmployee) {
        return employeeRespositroy.save(theEmployee);
    }

    @Override
    public void deleteById(int theId) {
        employeeRespositroy.deleteById(theId);
    }
}
