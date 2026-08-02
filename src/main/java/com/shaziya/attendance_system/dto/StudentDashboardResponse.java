package com.shaziya.attendance_system.dto;

public class StudentDashboardResponse {

    private String studentName;
    private String course;
    private Integer semester;

    private long classesAttended;
    private long totalClasses;

    private double attendancePercentage;

    public StudentDashboardResponse(
            String studentName,
            String course,
            Integer semester,
            long classesAttended,
            long totalClasses,
            double attendancePercentage
    ) {
        this.studentName = studentName;
        this.course = course;
        this.semester = semester;
        this.classesAttended = classesAttended;
        this.totalClasses = totalClasses;
        this.attendancePercentage = attendancePercentage;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getCourse() {
        return course;
    }

    public Integer getSemester() {
        return semester;
    }

    public long getClassesAttended() {
        return classesAttended;
    }

    public long getTotalClasses() {
        return totalClasses;
    }

    public double getAttendancePercentage() {
        return attendancePercentage;
    }
}