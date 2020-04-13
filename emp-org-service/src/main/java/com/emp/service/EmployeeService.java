package com.emp.service;

import com.emp.model.Department;
import com.emp.model.Employee;

import java.util.List;

public interface EmployeeService {
    public void createUser(Employee employee);
    public List<Employee> getEmployeeList();
    public Employee findById(long id);
    public List<Employee> getEmployeeListByDeptId(long deptId);
//    public Employee update(Employee employee, long l);
//    public void deleteUserById(long id);
//    public Employee updatePartially(Employee employee, long id);
//    public List<Department> getDepartments();
//	public Employee findByEname(String string);
}
