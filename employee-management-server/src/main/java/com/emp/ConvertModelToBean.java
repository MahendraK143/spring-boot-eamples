package com.emp;

import com.emp.bean.DepartmentBean;
import com.emp.bean.EmployeeBean;
import com.emp.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class ConvertModelToBean {
    public static List<EmployeeBean> convertModelToBean(List<Employee> employees){
        List<EmployeeBean> beans = new ArrayList<>();
        employees.stream().forEach(employee -> {
            EmployeeBean bean = new EmployeeBean();
            DepartmentBean departmentBean = new DepartmentBean();
            departmentBean.setDepartmentId(bean.getDepartment().getDepartmentId());
            departmentBean.setDepartmentName(bean.getDepartment().getDepartmentName());
            departmentBean.setLocation(bean.getDepartment().getLocation());
            bean.setDepartment(departmentBean);
            bean.setEmployeeId(employee.getEmployeeId());
            bean.setEname(employee.getEname());
            bean.setHiredate(employee.getHiredate());
            bean.setJob(employee.getJob());
            //bean.setManager(employee.getManager());
            bean.setSalary(employee.getSalary());
            beans.add(bean);
        });
        return beans;
    }
}
