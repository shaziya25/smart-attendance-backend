package com.shaziya.attendance_system.repository;

import com.shaziya.attendance_system.entity.Attendance;
import com.shaziya.attendance_system.entity.Faculty;
import com.shaziya.attendance_system.entity.Lecture;
import com.shaziya.attendance_system.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository
        extends JpaRepository<Attendance, Long> {

    Optional<Attendance> findByStudentAndLecture(
            Student student,
            Lecture lecture
    );

    List<Attendance> findByStudent(Student student);

    List<Attendance> findByLecture(Lecture lecture);

    List<Attendance> findAllByOrderByMarkedAtDesc();

    List<Attendance> findByMarkedAtBetween(
            LocalDateTime start,
            LocalDateTime end
    );

    long countByStudent(Student student);

    long countByLectureFaculty(Faculty faculty);
}