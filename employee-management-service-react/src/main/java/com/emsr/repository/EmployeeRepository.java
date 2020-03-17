package com.emsr.repository;

import com.emsr.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    //    @Query("SELECT e FROM employee e where e.departmentId = :departmentId")
    public List<Employee> findByDepartment_DepartmentId(@Param("departmentId") Long deptId);
//    public List<Employee> findByEname(@Param("ename") String deptId);

    public Employee findByEname(String ename);

    public List<Employee> findByManager(@Param("manager") String manager);

}
