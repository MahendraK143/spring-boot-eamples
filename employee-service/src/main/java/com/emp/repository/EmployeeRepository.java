package com.emp.repository;

import com.emp.bean.EmployeeBean;
import com.emp.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
//    @Query("SELECT e FROM employee e where e.departmentId = :departmentId")
    public List<Employee> findByDepartment_DepartmentId(@Param("departmentId") Long deptId);
//    public List<Employee> findByEname(@Param("ename") String deptId);
   
	public Employee findByEname(String ename);

}
