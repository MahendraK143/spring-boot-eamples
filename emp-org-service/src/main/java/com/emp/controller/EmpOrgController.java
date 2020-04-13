package com.emp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.emp.model.Department;
import com.emp.model.Employee;
import com.emp.service.EmployeeService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("employee")
public class EmpOrgController {
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/list")
	public @ResponseBody List<Employee> employees() {
		return employeeService.getEmployeeList();
	}

	@GetMapping("/fault-tolerance-list")
	// configuring a fallback method
	@HystrixCommand(fallbackMethod = "fallbackRetrieveConfigurations")
	public @ResponseBody List<Employee> retrieveConfigurations() {
		throw new RuntimeException("Not Available");
	}

	// defining the fallback method
	public List<Employee> fallbackRetrieveConfigurations() {
		// returning the default configuration
		List<Employee> list = new ArrayList<Employee>();
		Employee employee = new Employee();
		employee.setEmployeeId(1);
		employee.setEname("username");
		employee.setHiredate("hiredate");
		employee.setJob("job");
		employee.setManager("manager");
		employee.setSalary(6780987);
		Department department = new Department();
		department.setDepartmentId(1);
		department.setDepartmentName("departmentName");
		department.setLocation("location");
		employee.setDepartment(department);
		list.add(employee);
		return list;
	}
}
