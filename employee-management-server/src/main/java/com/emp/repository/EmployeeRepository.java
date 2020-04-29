package com.emp.repository;

import com.emp.bean.EmployeeBean;
import com.emp.model.DeptEmp1;
import com.emp.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    @Query(value = "SELECT e.* FROM employee e where e.department_id = :departmentId",nativeQuery = true)
    public List<Employee> findByDepartment_DepartmentId(@Param("departmentId") Long deptId);
//    public List<Employee> findByEname(@Param("ename") String deptId);
   
	public Employee findByEname(String ename);
	@Query(value = "SELECT d.location,d.department_name as departmentName, e.job as job, e.ename as ename FROM  employee e JOIN department d where e.department_id = d.department_id and e.department_id= :departmentId",nativeQuery = true)
	public List<DeptEmp1> findEmployeeListByInnerJoin(@Param("departmentId") Long deptId);

}
