package com.shaziya.attendance_system.service;
import com.shaziya.attendance_system.dto.ScanAttendanceRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.shaziya.attendance_system.dto.AttendanceResponse;
import com.shaziya.attendance_system.dto.CreateAttendanceRequest;
import com.shaziya.attendance_system.entity.Attendance;
import com.shaziya.attendance_system.entity.Lecture;
import com.shaziya.attendance_system.entity.Student;
import com.shaziya.attendance_system.repository.AttendanceRepository;
import com.shaziya.attendance_system.repository.LectureRepository;
import com.shaziya.attendance_system.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final LectureRepository lectureRepository;

    public AttendanceService(
            AttendanceRepository attendanceRepository,
            StudentRepository studentRepository,
            LectureRepository lectureRepository
    ) {
        this.attendanceRepository = attendanceRepository;
        this.studentRepository = studentRepository;
        this.lectureRepository = lectureRepository;
    }

    // =========================
    // MARK ATTENDANCE
    // =========================
    public AttendanceResponse markAttendance(CreateAttendanceRequest request) {

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));

        Lecture lecture = lectureRepository.findById(request.getLectureId())
                .orElseThrow(() ->
                        new RuntimeException("Lecture not found"));

        attendanceRepository.findByStudentAndLecture(student, lecture)
                .ifPresent(a -> {
                    throw new RuntimeException("Attendance already marked");
                });

        Attendance attendance = new Attendance(
                LocalDateTime.now(),
                student,
                lecture
        );

        Attendance savedAttendance = attendanceRepository.save(attendance);

        return new AttendanceResponse(
                savedAttendance.getId(),
                student.getUser().getName(),
                lecture.getSubject(),
                savedAttendance.getMarkedAt()
        );
    }

    // =========================
    // ALL ATTENDANCE HISTORY
    // =========================
    public List<AttendanceResponse> getAttendanceHistory() {

        return attendanceRepository
                .findAllByOrderByMarkedAtDesc()
                .stream()
                .map(attendance -> new AttendanceResponse(
                        attendance.getId(),
                        attendance.getStudent().getUser().getName(),
                        attendance.getLecture().getSubject(),
                        attendance.getMarkedAt()
                ))
                .toList();
    }

    // =========================
    // ATTENDANCE BY DATE
    // =========================
    public List<AttendanceResponse> getAttendanceByDate(LocalDate date) {

        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays(1).atStartOfDay();

        return attendanceRepository
                .findByMarkedAtBetween(start, end)
                .stream()
                .map(attendance -> new AttendanceResponse(
                        attendance.getId(),
                        attendance.getStudent().getUser().getName(),
                        attendance.getLecture().getSubject(),
                        attendance.getMarkedAt()
                ))
                .toList();
    }
    public List<AttendanceResponse>
    getAttendanceByLecture(Long lectureId) {

        Lecture lecture = lectureRepository
                .findById(lectureId)
                .orElseThrow(() ->
                        new RuntimeException("Lecture not found"));

        return attendanceRepository
                .findByLecture(lecture)
                .stream()
                .map(attendance -> new AttendanceResponse(
                        attendance.getId(),
                        attendance.getStudent()
                                .getUser()
                                .getName(),
                        attendance.getLecture()
                                .getSubject(),
                        attendance.getMarkedAt()
                ))
                .toList();
    }
    public AttendanceResponse scanAttendance(ScanAttendanceRequest request) {

        // Get logged-in user's email from JWT
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        // Find student by email
        Student student = studentRepository.findByUserEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));

        // Find lecture
        Lecture lecture = lectureRepository.findById(request.getLectureId())
                .orElseThrow(() ->
                        new RuntimeException("Lecture not found"));

        // Prevent duplicate attendance
        attendanceRepository.findByStudentAndLecture(student, lecture)
                .ifPresent(a -> {
                    throw new RuntimeException("Attendance already marked");
                });

        // Save attendance
        Attendance attendance = new Attendance(
                LocalDateTime.now(),
                student,
                lecture
        );

        Attendance savedAttendance = attendanceRepository.save(attendance);

        return new AttendanceResponse(
                savedAttendance.getId(),
                student.getUser().getName(),
                lecture.getSubject(),
                savedAttendance.getMarkedAt()
        );
    }
    public List<AttendanceResponse>
    getMyAttendance() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email = authentication.getName();

        Student student = studentRepository
                .findByUserEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));

        return attendanceRepository
                .findByStudent(student)
                .stream()
                .map(attendance -> new AttendanceResponse(
                        attendance.getId(),
                        attendance.getStudent()
                                .getUser()
                                .getName(),
                        attendance.getLecture()
                                .getSubject(),
                        attendance.getMarkedAt()
                ))
                .toList();
    }
}