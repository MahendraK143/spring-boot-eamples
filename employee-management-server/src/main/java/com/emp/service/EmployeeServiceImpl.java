package com.emp.service;

import com.emp.bean.DepartmentBean;
import com.emp.model.Department;
import com.emp.model.DeptEmp1;
import com.emp.model.Employee;
import com.emp.repository.DepartmentRepository;
import com.emp.repository.EmployeeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    public void createUser(Employee employee) {
        // TODO Auto-generated method stub
        departmentRepository.save(employee.getDepartment());
        employeeRepository.save(employee);
    }

    public List<Employee> getEmployeeList() {
        // TODO Auto-generated method stub
        return (List<Employee>) employeeRepository.findAll();
    }
    public List<Employee> getEmployeeListByDeptId(long deptId) {
        // TODO Auto-generated method stub
        return (List<Employee>) employeeRepository.findByDepartment_DepartmentId(deptId);
        //return (List<Employee>) departmentRepository.findByDepartmentId(deptId);
    }
    @Override
    public List<DeptEmp1> getEmployeeListByInnerJoin(long deptId) {
    	// TODO Auto-generated method stub
    	return employeeRepository.findEmployeeListByInnerJoin(deptId);
    }

    public Employee findById(long id) {
        // TODO Auto-generated method stub
        return (employeeRepository.findById(id)).get();
    }
    
    public Employee findByEname(String ename) {
        // TODO Auto-generated method stub
    	Employee emp = employeeRepository.findByEname(ename);
    	LOGGER.info("Employee:"+emp);
        return emp != null ? emp : null;
    }

    public Employee update(Employee employee, long l) {
        // TODO Auto-generated method stub
        return employeeRepository.save(employee);
    }

    public void deleteUserById(long id) {
        // TODO Auto-generated method stub
        employeeRepository.deleteById(id);
    }

    public Employee updatePartially(Employee employee, long id) {
        // TODO Auto-generated method stub
        Employee emp = findById(id);
        //emp.setPassword(user.getPassword());
        return employeeRepository.save(emp);
    }

    @Override
    public List<Department> getDepartments() {
        return (List<Department>) departmentRepository.findAll();
    }

}
