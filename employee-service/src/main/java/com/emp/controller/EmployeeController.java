package com.emp.controller;

import com.emp.bean.DepartmentBean;
import com.emp.bean.EmployeeBean;
import com.emp.bean.MessageBean;
import com.emp.model.Department;
import com.emp.model.Employee;
import com.emp.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "employee")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    EmployeeService employeeService;
    @Value("${test.name}")
    private String testName;

    @PostMapping(value = "/login")
    public ResponseEntity<Employee> login(@RequestBody() Employee employee){
        Employee emp = employeeService.findByEname(employee.getEname());
        if( emp != null && emp.getPassword().equals(employee.getPassword()))
        	return new ResponseEntity<Employee>(emp, HttpStatus.OK);
        return new ResponseEntity<Employee>(emp, HttpStatus.NOT_FOUND);
    }
    
    @GetMapping(value = "/list")
    public ResponseEntity<List<Employee>> getEmployeeList(final HttpServletRequest request){
        List<Employee> employees = employeeService.getEmployeeList();
        employees = employees.stream().map(emp -> {
        	emp.setEname(emp.getEname()+request.getServerPort()+request.getContextPath()+testName	);
        	return emp;
        }).collect(Collectors.toList());
//        		employees.stream().forEach(employee -> {
//            LOGGER.info(employee.getManager());
//            int empId = Integer.parseInt(employee.getManager());
//            if(empId != 0) {
//            	Employee ee = employeeService.findById(empId);
//            	
//            	employee.setManager(ee.getEname());
//            }
//                
//        });

        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }

    @GetMapping(value = "/listByDepartmentId/{deptId}")
    public ResponseEntity<List<Employee>> getEmployeeList(@PathVariable long deptId){
        List<Employee> employees = employeeService.getEmployeeListByDeptId(deptId);
//        .stream().map(employee -> {
//            LOGGER.info(employee.getManager());
//            int empId = Integer.parseInt(employee.getManager());
//            if(empId != 0)
//                employee.setManager(employeeService.findById(empId).getEname());
//            return employee;
//        }).collect(Collectors.toList());

        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }
    @GetMapping(value = "/departments")
    public ResponseEntity<List<Department>> getDepartments(){
        return new ResponseEntity<List<Department>>(employeeService.getDepartments(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getUserById(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        Employee user = employeeService.findById(id);
        if (user == null) {
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Employee>(user, HttpStatus.OK);
    }
    @PostMapping(value = "/save")
    public ResponseEntity<MessageBean> save(@RequestBody Employee employee, UriComponentsBuilder ucBuilder){
        System.out.println("Creating User "+employee.getEname());
        String msg = null;
        employee.setEmployeeId(0);
        MessageBean messageBean = new MessageBean();
        if(employeeService.findByEname(employee.getEname()) !=null ) {
        	employeeService.createUser(employee);
//            HttpHeaders headers = new HttpHeaders();
//            headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(employee.getEmployeeId()).toUri());
        	messageBean.setMessage("The employee '"+employee.getEname()+"' is created successfully");
        	messageBean.setType("Success");
        	return new ResponseEntity<MessageBean>(messageBean, HttpStatus.OK);
        } 
        messageBean.setMessage("The employee name '"+employee.getEname()+"' is already existed");
        messageBean.setType("Found");
        return new ResponseEntity<MessageBean>(messageBean, HttpStatus.FOUND);
    }

    @PutMapping(value="/update", headers="Accept=application/json")
    public ResponseEntity<String> updateUser(@RequestBody Employee currentUser)
    {
        System.out.println("sd");
        Employee user = employeeService.findById(currentUser.getEmployeeId());
        if (user==null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        employeeService.update(currentUser, currentUser.getEmployeeId());
        return new ResponseEntity<String>(HttpStatus.OK);
    }
    @DeleteMapping(value="/{id}", headers ="Accept=application/json")
    public ResponseEntity<Employee> deleteUser(@PathVariable("id") long id){
        Employee user = employeeService.findById(id);
        if (user == null) {
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
        employeeService.deleteUserById(id);
        return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value="/{id}", headers="Accept=application/json")
    public ResponseEntity<Employee> updateUserPartially(@PathVariable("id") long id, @RequestBody Employee currentEmp){
        Employee user = employeeService.findById(id);
        if(user ==null){
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
        Employee usr =	employeeService.updatePartially(currentEmp, id);
        return new ResponseEntity<Employee>(usr, HttpStatus.OK);
    }
}
