package ru.shmoylova.tracker.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import ru.shmoylova.tracker.interfaces.dao.BaseEntity;

public class Role implements Serializable, BaseEntity {

    private static final long serialVersionUID = 1L;
    private int roleId;
    private String roleName;
    private Set<GroupMember> groupMembers = new HashSet<>(0);
    private Set<Employee> employees = new HashSet<>(0);

    public Role() {
    }

    public Role(int roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public Role(int roleId, String roleName, Set<GroupMember> groupMembers, Set<Employee> employees) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.groupMembers = groupMembers;
        this.employees = employees;
    }

    public int getRoleId() {
        return this.roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<GroupMember> getGroupMembers() {
        return this.groupMembers;
    }

    public void setGroupMembers(Set<GroupMember> groupMembers) {
        this.groupMembers = groupMembers;
    }

    public Set<Employee> getEmployees() {
        return this.employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

}
