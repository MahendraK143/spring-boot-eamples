package com.emp.repository;

import com.emp.bean.DepartmentBean;
import com.emp.model.Department;
import com.emp.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
    //@Query("select e from employee as e, e.department as d where d.departmentId=:departmentId")
    //public List<Employee> findByDepartmentId(@Param("departmentId") Long deptId);
}
