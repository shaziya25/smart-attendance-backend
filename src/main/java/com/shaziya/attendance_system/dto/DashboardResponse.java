package com.shaziya.attendance_system.dto;

public class DashboardResponse {

    private long totalStudents;
    private long totalFaculty;
    private long totalLectures;
    private long totalAttendance;

    public DashboardResponse(
            long totalStudents,
            long totalFaculty,
            long totalLectures,
            long totalAttendance
    ) {
        this.totalStudents = totalStudents;
        this.totalFaculty = totalFaculty;
        this.totalLectures = totalLectures;
        this.totalAttendance = totalAttendance;
    }

    public long getTotalStudents() {
        return totalStudents;
    }

    public long getTotalFaculty() {
        return totalFaculty;
    }

    public long getTotalLectures() {
        return totalLectures;
    }

    public long getTotalAttendance() {
        return totalAttendance;
    }
}