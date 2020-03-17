package com.emsr.repository;

import com.emsr.model.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {
    //@Query("select e from employee as e, e.department as d where d.departmentId=:departmentId")
    //public List<Employee> findByDepartmentId(@Param("departmentId") Long deptId);
}
