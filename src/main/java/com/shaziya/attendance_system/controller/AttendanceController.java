package com.shaziya.attendance_system.controller;
import com.shaziya.attendance_system.dto.ScanAttendanceRequest;
import com.shaziya.attendance_system.dto.AttendanceResponse;
import com.shaziya.attendance_system.dto.CreateAttendanceRequest;
import com.shaziya.attendance_system.service.AttendanceService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(
            AttendanceService attendanceService
    ) {
        this.attendanceService = attendanceService;
    }

    // =========================
    // MARK ATTENDANCE
    // =========================
    @PostMapping("/mark")
    public ResponseEntity<AttendanceResponse> markAttendance(
            @RequestBody CreateAttendanceRequest request
    ) {

        return ResponseEntity.ok(
                attendanceService.markAttendance(request)
        );
    }
    @PostMapping("/scan")
    public ResponseEntity<AttendanceResponse> scanAttendance(
            @RequestBody ScanAttendanceRequest request
    ) {

        return ResponseEntity.ok(
                attendanceService.scanAttendance(request)
        );
    }

    // =========================
    // GET ALL ATTENDANCE
    // =========================
    @GetMapping("/history")
    public ResponseEntity<List<AttendanceResponse>> getAttendanceHistory() {

        return ResponseEntity.ok(
                attendanceService.getAttendanceHistory()
        );
    }

    // =========================
    // GET ATTENDANCE BY DATE
    // =========================
    @GetMapping("/history/date")
    public ResponseEntity<List<AttendanceResponse>> getAttendanceByDate(

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date

    ) {

        return ResponseEntity.ok(
                attendanceService.getAttendanceByDate(date)
        );
    }
    @GetMapping("/lecture/{lectureId}")
    public ResponseEntity<List<AttendanceResponse>>
    getAttendanceByLecture(
            @PathVariable Long lectureId
    ) {

        System.out.println("ATTENDANCE BY LECTURE API HIT");

        return ResponseEntity.ok(
                attendanceService.getAttendanceByLecture(lectureId)
        );
    }
    @GetMapping("/my-attendance")
    public ResponseEntity<List<AttendanceResponse>>
    getMyAttendance() {

        return ResponseEntity.ok(
                attendanceService.getMyAttendance()
        );
    }
}