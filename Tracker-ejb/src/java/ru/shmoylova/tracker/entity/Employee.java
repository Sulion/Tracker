package ru.shmoylova.tracker.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import ru.shmoylova.tracker.interfaces.dao.BaseEntity;

public class Employee implements Serializable, BaseEntity {

    private static final long serialVersionUID = 1L;
    private int empId;
    private Department department;
    private Permission permission;
    private Role role;
    private String lastName;
    private String firstName;
    private String surName;
    private String jobTitle;
    private String login;
    private String pass;
    private Set<ProductionUnit> productionUnits = new HashSet<>(0);
    private Set<Activity> activities = new HashSet<>(0);

    public Employee() {
    }

    public Employee(int empId, Department department, Permission permission, Role role, String lastName, String firstName, String jobTitle, String login, String pass) {
        this.empId = empId;
        this.department = department;
        this.permission = permission;
        this.role = role;
        this.lastName = lastName;
        this.firstName = firstName;
        this.jobTitle = jobTitle;
        this.login = login;
        this.pass = pass;
    }

    public Employee(int empId, Department department, Permission permission, Role role, String lastName, String firstName, String surName, String jobTitle, String login, String pass, Set<ProductionUnit> productionUnits, Set<Activity> activities) {
        this.empId = empId;
        this.department = department;
        this.permission = permission;
        this.role = role;
        this.lastName = lastName;
        this.firstName = firstName;
        this.surName = surName;
        this.jobTitle = jobTitle;
        this.login = login;
        this.pass = pass;
        this.productionUnits = productionUnits;
        this.activities = activities;
    }

    public int getEmpId() {
        return this.empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Permission getPermission() {
        return this.permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return this.surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getJobTitle() {
        return this.jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return this.pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Set<ProductionUnit> getProductionUnits() {
        return this.productionUnits;
    }

    public void setProductionUnits(Set<ProductionUnit> productionUnits) {
        this.productionUnits = productionUnits;
    }

    public Set<Activity> getActivities() {
        return this.activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }

}
