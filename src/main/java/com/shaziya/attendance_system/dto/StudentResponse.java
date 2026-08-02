package com.shaziya.attendance_system.dto;

public class StudentResponse {

    private Long id;
    private String rollNumber;
    private String course;
    private int semester;
    private String name;
    private String email;
    private String role;

    public StudentResponse(
            Long id,
            String rollNumber,
            String course,
            int semester,
            String name,
            String email,
            String role
    ) {
        this.id = id;
        this.rollNumber = rollNumber;
        this.course = course;
        this.semester = semester;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getCourse() {
        return course;
    }

    public int getSemester() {
        return semester;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}