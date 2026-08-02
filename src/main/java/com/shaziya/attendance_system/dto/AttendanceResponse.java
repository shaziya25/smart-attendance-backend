package com.shaziya.attendance_system.dto;

import java.time.LocalDateTime;

public class AttendanceResponse {

    private Long id;
    private String studentName;
    private String lectureSubject;
    private LocalDateTime markedAt;

    public AttendanceResponse() {
    }

    public AttendanceResponse(
            Long id,
            String studentName,
            String lectureSubject,
            LocalDateTime markedAt
    ) {
        this.id = id;
        this.studentName = studentName;
        this.lectureSubject = lectureSubject;
        this.markedAt = markedAt;
    }

    public Long getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getLectureSubject() {
        return lectureSubject;
    }

    public LocalDateTime getMarkedAt() {
        return markedAt;
    }
}