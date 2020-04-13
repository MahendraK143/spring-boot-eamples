package com.emp.service;

import com.emp.model.Employee;

import io.micrometer.core.instrument.binder.BaseUnits;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	Logger LOGGER = LoggerFactory.getLogger(this.getClass());
//	@Autowired
	private RestTemplate restTemplate = new RestTemplate();
	@Autowired
	private DiscoveryClient discoveryClient;
	@Autowired
	private EmployeeServiceProxy employeeServiceProxy;
	public void createUser(Employee employee) {
		List<ServiceInstance> instances=discoveryClient.getInstances("employee-server");
		ServiceInstance serviceInstance=instances.get(0);
		
		String baseURL=serviceInstance.getUri().toString();
    	restTemplate.exchange(discoveryClient.getInstances("employee-server").get(0).getUri().toString()+"/employee/list", HttpMethod.GET, getHeaders(),Employee.class);
	}
	public List<Employee> getEmployeeList() {
        // TODO Auto-generated method stub
		// ResponseEntity<Employee[]> response = restTemplate.exchange(discoveryClient.getInstances("employee-server").get(0).getUri().toString()+"/employee/list", HttpMethod.GET, getHeaders(),Employee[].class);
		// return Arrays.asList(response.getBody());
		LOGGER.info("before called employee-service for getting list of employees");
		List<Employee> list =  employeeServiceProxy.getEmployeeList();
		 LOGGER.info("emp list from emp-org-service :{}",list);
		LOGGER.info("after called employee-service for getting list of employees");
		return list;
	}
    private HttpEntity getHeaders() {
    	HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}

	
    public List<Employee> getEmployeeListByDeptId(long deptId) {
        // TODO Auto-generated method stub
        return null;
        //return (List<Employee>) departmentRepository.findByDepartmentId(deptId);
    }

    public Employee findById(long id) {
        // TODO Auto-generated method stub
        return null;
    }
    
    public Employee findByEname(String ename) {
        // TODO Auto-generated method stub
    	Employee emp = null;//employeeRepository.findByEname(ename);
    	LOGGER.info("Employee:"+emp);
        return emp != null ? emp : null;
    }

//    public Employee update(Employee employee, long l) {
//        // TODO Auto-generated method stub
//        return employeeRepository.save(employee);
//    }
//
//    public void deleteUserById(long id) {
//        // TODO Auto-generated method stub
//        employeeRepository.deleteById(id);
//    }
//
//    public Employee updatePartially(Employee employee, long id) {
//        // TODO Auto-generated method stub
//        Employee emp = findById(id);
//        //emp.setPassword(user.getPassword());
//        return employeeRepository.save(emp);
//    }
//
//    @Override
//    public List<Department> getDepartments() {
//        return (List<Department>) departmentRepository.findAll();
//    }

}
