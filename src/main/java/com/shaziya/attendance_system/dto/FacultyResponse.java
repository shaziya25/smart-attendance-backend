package com.shaziya.attendance_system.dto;

public class FacultyResponse {

    private Long id;
    private String name;
    private String email;
    private String employeeId;
    private String department;
    private String designation;

    public FacultyResponse(
            Long id,
            String name,
            String email,
            String employeeId,
            String department,
            String designation
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.employeeId = employeeId;
        this.department = department;
        this.designation = designation;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getDepartment() {
        return department;
    }

    public String getDesignation() {
        return designation;
    }
}