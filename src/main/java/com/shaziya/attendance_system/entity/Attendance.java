package com.shaziya.attendance_system.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime markedAt;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    public Attendance() {
    }

    public Attendance(
            LocalDateTime markedAt,
            Student student,
            Lecture lecture
    ) {
        this.markedAt = markedAt;
        this.student = student;
        this.lecture = lecture;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getMarkedAt() {
        return markedAt;
    }

    public Student getStudent() {
        return student;
    }

    public Lecture getLecture() {
        return lecture;
    }
}