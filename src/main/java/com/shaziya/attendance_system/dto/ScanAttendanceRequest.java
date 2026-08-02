package com.shaziya.attendance_system.dto;

public class ScanAttendanceRequest {

    private Long lectureId;

    public ScanAttendanceRequest() {
    }

    public Long getLectureId() {
        return lectureId;
    }

    public void setLectureId(Long lectureId) {
        this.lectureId = lectureId;
    }
}