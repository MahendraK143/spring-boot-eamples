package com.emsr.controller;

import com.emsr.bean.MessageBean;
import com.emsr.model.Department;
import com.emsr.model.Employee;
import com.emsr.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "employee")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    EmployeeService employeeService;

    @PostMapping(value = "/login")
    public ResponseEntity<Employee> login(@RequestBody() Employee employee) {
        Employee emp = employeeService.findByEname(employee.getEname());
        if (emp != null && emp.getPassword().equals(employee.getPassword()))
            return new ResponseEntity<Employee>(emp, HttpStatus.OK);
        return new ResponseEntity<Employee>(emp, HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/login1")
    public ResponseEntity<Employee> login1(@RequestParam() String username, @RequestParam() String password) {
        Employee emp = employeeService.findByEname(username);
        if (emp != null && emp.getPassword().equals(password))
            return new ResponseEntity<Employee>(emp, HttpStatus.OK);
        return new ResponseEntity<Employee>(emp, HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<Employee>> getEmployeeList() {
        List<Employee> employees = employeeService.getEmployeeList();
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
    public ResponseEntity<List<Employee>> getEmployeeList(@PathVariable long deptId) {
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
    public ResponseEntity<List<Department>> getDepartments() {
        return new ResponseEntity<List<Department>>(employeeService.getDepartments(), HttpStatus.OK);
    }

    @GetMapping(value = "/department/{departmentId}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable long departmentId) {
        return new ResponseEntity<Department>(employeeService.getDepartmentById(departmentId), HttpStatus.OK);
    }

    @GetMapping(value = "/managerList")
    public ResponseEntity<List<Employee>> managerList() {
        return new ResponseEntity<List<Employee>>(employeeService.getManagerList(), HttpStatus.OK);
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
    public ResponseEntity<MessageBean> save(@RequestBody Employee employee, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + employee);
        String msg = null;
        employee.setEmployeeId(0);
        MessageBean messageBean = new MessageBean();

        if (employeeService.findByEname(employee.getEname()) == null) {
            employee.setDepartment(employeeService.getDepartmentById(employee.getDepartment().getDepartmentId()));
            employeeService.createUser(employee);
//            HttpHeaders headers = new HttpHeaders();
//            headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(employee.getEmployeeId()).toUri());
            messageBean.setMessage("The employee '" + employee.getEname() + "' is created successfully");
            messageBean.setType("Success");
            return new ResponseEntity<MessageBean>(messageBean, HttpStatus.OK);
        }
        messageBean.setMessage("The employee name '" + employee.getEname() + "' is already existed");
        messageBean.setType("Found");
        return new ResponseEntity<MessageBean>(messageBean, HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<MessageBean> updateEmployee(@RequestBody Employee employee, UriComponentsBuilder ucBuilder) {
        System.out.println("Update User " + employee);
        String msg = null;
        MessageBean messageBean = new MessageBean();

        if (employeeService.findByEname(employee.getEname()) != null && employee.getEmployeeId() != 0) {
            employee.setDepartment(employeeService.getDepartmentById(employee.getDepartment().getDepartmentId()));
            employeeService.createUser(employee);
//            HttpHeaders headers = new HttpHeaders();
//            headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(employee.getEmployeeId()).toUri());
            messageBean.setMessage("The employee '" + employee.getEname() + "' is updated successfully");
            messageBean.setType("Success");
            return new ResponseEntity<MessageBean>(messageBean, HttpStatus.OK);
        }
        messageBean.setMessage("The employee name '" + employee.getEname() + "' is already existed");
        messageBean.setType("Found");
        return new ResponseEntity<MessageBean>(messageBean, HttpStatus.OK);
    }

    @PutMapping(value = "/update", headers = "Accept=application/json")
    public ResponseEntity<String> updateUser(@RequestBody Employee currentUser) {
        System.out.println("sd");
        Employee user = employeeService.findById(currentUser.getEmployeeId());
        if (user == null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        employeeService.update(currentUser, currentUser.getEmployeeId());
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}", headers = "Accept=application/json")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") long id) {
        Employee user = employeeService.findById(id);
        if (user == null) {
            return new ResponseEntity<Object>("Employee is not available!", HttpStatus.NOT_FOUND);
        }
        employeeService.deleteUserById(id);
        return new ResponseEntity<Object>("Employee deleted successfully!", HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}", headers = "Accept=application/json")
    public ResponseEntity<Employee> updateUserPartially(@PathVariable("id") long id, @RequestBody Employee currentEmp) {
        Employee user = employeeService.findById(id);
        if (user == null) {
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
        Employee usr = employeeService.updatePartially(currentEmp, id);
        return new ResponseEntity<Employee>(usr, HttpStatus.OK);
    }
}
