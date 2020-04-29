package com.emp.bean;

import lombok.*;

import javax.persistence.*;

public class EmployeeBean {
    private long employeeId;
    private String ename;
    private String job;
    private String manager;
    private String hiredate;
    private long salary;
    private DepartmentBean department;

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getHiredate() {
        return hiredate;
    }

    public void setHiredate(String hiredate) {
        this.hiredate = hiredate;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public DepartmentBean getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentBean department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", ename='" + ename + '\'' +
                ", job='" + job + '\'' +
                ", manager=" + manager +
                ", hiredate='" + hiredate + '\'' +
                ", salary=" + salary +
                ", department=" + department +
                '}';
    }
}
