package com.emp.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import com.emp.model.Department;
import com.emp.model.Employee;

import feign.FeignException;
import feign.hystrix.FallbackFactory;

//@FeignClient(name="employee-service", fallback = EmployeeFallback.class)
//@FeignClient(name = "employee-service", url = ="localhost:8080")
@FeignClient(name = "emp-netflix-zuul-api-gateway-server", fallbackFactory = EmployeeFallbackFactory.class)
//@FeignClient(name = "emp-netflix-zuul-api-gateway-server")
@RibbonClient(name = "employee-service")
public interface EmployeeServiceProxy {
//	@GetMapping(value = "/employee/list")
	@GetMapping(value = "/employee-service/employee/list")
	public List<Employee> getEmployeeList();
}

@Component
class EmployeeFallbackFactory implements FallbackFactory<EmployeeServiceProxy> {

	@Override
	public EmployeeServiceProxy create(Throwable cause) {
		return new EmployeeServiceProxyFallback(cause);
	}

}

class EmployeeServiceProxyFallback implements EmployeeServiceProxy {
	Logger logger = LoggerFactory.getLogger(EmployeeServiceProxyFallback.class);
	private Throwable cause;;

	public EmployeeServiceProxyFallback(Throwable cause) {
		this.cause = cause;
	}

	@Override
	public List<Employee> getEmployeeList() {
		if (cause instanceof FeignException && ((FeignException) cause).status() == 404) {
			logger.error("404 error took a place when getEmployeeList was called. Error Message: "
					+ cause.getLocalizedMessage());
		} else {
			logger.error("other error took a place: " + cause.getLocalizedMessage());
		}
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

//@Component
//class EmployeeFallback implements EmployeeServiceProxy{
//
//	@Override
//	public List<Employee> getEmployeeList() {
//		// TODO Auto-generated method stub
//		return new ArrayList<Employee>();
//	}
//	
//}
