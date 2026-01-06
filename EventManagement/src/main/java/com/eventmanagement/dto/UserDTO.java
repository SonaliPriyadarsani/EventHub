package com.eventmanagement.dto;
public class UserDTO {

    private Long userId;
    private String name;
    private String email;
    private String role;
    private String department;
    private boolean active;

    public UserDTO() {}

    public UserDTO(Long userId, String name, String email,
                   String role, String department, boolean active) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.role = role;
        this.department = department;
        this.active = active;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getRole() {
        return role;
    }
 
    public void setRole(String role) {
        this.role = role;
    }
 
    public String getDepartment() {
        return department;
    }
 
    public void setDepartment(String department) {
        this.department = department;
    }

    public boolean isActive() {
        return active;
    }
 
    public void setActive(boolean active) {
        this.active = active;
    }
}

