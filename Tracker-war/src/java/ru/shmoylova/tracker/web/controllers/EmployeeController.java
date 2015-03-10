package ru.shmoylova.tracker.web.controllers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import ru.shmoylova.tracker.entity.Department;
import ru.shmoylova.tracker.entity.Employee;
import ru.shmoylova.tracker.entity.Permission;
import ru.shmoylova.tracker.entity.Role;
import ru.shmoylova.tracker.interfaces.beans.EmployeeSessionBeanLocal;

@ManagedBean
@RequestScoped
public class EmployeeController {

    @EJB
    EmployeeSessionBeanLocal employeeBean;
    private static final String ERROR_PREFIX_FOR_LIST = "Список сотрудников: ";
    private static final String ERROR_PREFIX_FOR_EDIT = "Добавление/изменение данных сотрудника: ";
    private static final String ERROR_PREFIX_FOR_DELETE = "Удаление данных сотрудника: ";
    private static final String PAGE_EMPLOYEE = "emplist";
    private static final String PAGE_INDEX = "index";
    private static final String PAGE_BROWSE = "browse";
    private static final String REQUEST_ERROR = "Ошибка при выполнении запроса - ";
    private String errorPrefix = ERROR_PREFIX_FOR_LIST;
    private int empId;
    private MessagesController printer = null;
    private Employee emp;
    private DataModel empList;
    private int startId = 1;
    private int endId = 4;
    private int recordCount = 1000;
    private int pageSize = 10;

    private Employee current;
    private int selectedItemIndex;

    public EmployeeController() {
        emp = new Employee();
        printer = new MessagesController();
    }

    public String save() {
        errorPrefix = ERROR_PREFIX_FOR_EDIT;
        try {
            employeeBean.insertOrUpdate(emp);
        } catch (Exception e) {
            printer.printError(errorPrefix + REQUEST_ERROR + e.getMessage());
        }
        return PAGE_EMPLOYEE;
    }

    public String delete() {
        errorPrefix = ERROR_PREFIX_FOR_DELETE;
        try {
            employeeBean.remove(emp);
        } catch (Exception e) {
            printer.printError(errorPrefix + REQUEST_ERROR + e.getMessage());
        }
        return PAGE_EMPLOYEE;
    }

    public String cancel() {
        return PAGE_EMPLOYEE;
    }

    public Employee getSelected() {
        if (current == null) {
            current = new Employee();
            selectedItemIndex = -1;
        }
        return current;
    }

    public DataModel getEmpList() {
        try {
            if (empList == null) {
                empList = new ListDataModel(employeeBean.getAllEmployees());
            }
        } catch (NullPointerException npe) {
            printer.printError(ERROR_PREFIX_FOR_LIST + REQUEST_ERROR);
        }
        return empList;
    }

    public String getDepartmentName() {
        String dept = current.getDepartment().getDeptName();
        return dept;
    }

    void recreateModel() {
        empList = null;
    }

    public boolean isHasNextPage() {
        if (endId + pageSize <= recordCount) {
            return true;
        }
        return false;
    }

    public boolean isHasPreviousPage() {
        if (startId - pageSize > 0) {
            return true;
        }
        return false;
    }

    public String next() {
        startId = endId + 1;
        endId = endId + pageSize;
        recreateModel();
        return PAGE_INDEX;
    }

    public String previous() {
        startId = startId - pageSize;
        endId = endId - pageSize;
        recreateModel();
        return PAGE_INDEX;
    }

    public int getPageSize() {
        return pageSize;
    }

    public String prepareView() {
        current = (Employee) getEmpList().getRowData();
        return PAGE_BROWSE;
    }

    public String prepareList() {
        // recreateModel();
        return PAGE_INDEX;
    }

    public int getEmpId() {
        return emp.getEmpId();
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public Department getDepartment() {
        return emp.getDepartment();
    }

    public void setDepartment(Department department) {
        this.emp.setDepartment(department);
    }

    public Permission getPermission() {
        return emp.getPermission();
    }

    public void setPermission(Permission permission) {
        this.emp.setPermission(permission);
    }

    public Role getRole() {
        return emp.getRole();
    }

    public void setRole(Role role) {
        this.emp.setRole(role);
    }

    public String getLastName() {
        return emp.getLastName();
    }

    public void setLastName(String lastName) {
        this.emp.setLastName(lastName);
    }

    public String getSurName() {
        return emp.getSurName();
    }

    public void setSurName(String surName) {
        this.emp.setSurName(surName);
    }

    public String getJobTitle() {
        return emp.getJobTitle();
    }

    public void setJobTitle(String jobTitle) {
        this.emp.setJobTitle(jobTitle);
    }

    public String getLogin() {
        return emp.getLogin();
    }

    public void setLogin(String login) {
        this.emp.setLogin(login);
    }

    public void setPass(String pass) {
        this.emp.setPass(pass);
    }

}
