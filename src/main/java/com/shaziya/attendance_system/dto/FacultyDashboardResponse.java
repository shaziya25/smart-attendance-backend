package com.shaziya.attendance_system.dto;

public class FacultyDashboardResponse {

    private String facultyName;
    private String department;
    private String designation;

    private long lecturesTaken;
    private long studentsPresent;

    public FacultyDashboardResponse(
            String facultyName,
            String department,
            String designation,
            long lecturesTaken,
            long studentsPresent
    ) {
        this.facultyName = facultyName;
        this.department = department;
        this.designation = designation;
        this.lecturesTaken = lecturesTaken;
        this.studentsPresent = studentsPresent;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public String getDepartment() {
        return department;
    }

    public String getDesignation() {
        return designation;
    }

    public long getLecturesTaken() {
        return lecturesTaken;
    }

    public long getStudentsPresent() {
        return studentsPresent;
    }
}