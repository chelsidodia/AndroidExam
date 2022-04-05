package com.example.testcrud1;

public class Employee {
    private int empid;
    private String empname;
    private String salary;
    private String designation;
    private String department;

    public Employee()
    {}

    public Employee(int empid, String empname, String salary, String designation, String department) {
        this.empid = empid;
        this.empname = empname;
        this.salary = salary;
        this.designation = designation;
        this.department = department;
    }

    public Employee(String empname, String salary, String designation, String department) {
        this.empname = empname;
        this.salary = salary;
        this.designation = designation;
        this.department = department;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public int getEmpid() {
        return empid;
    }

    public void setEmpid(int empid) {
        this.empid = empid;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

}
