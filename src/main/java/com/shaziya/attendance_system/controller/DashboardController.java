package com.shaziya.attendance_system.controller;
import com.shaziya.attendance_system.dto.StudentDashboardResponse;
import com.shaziya.attendance_system.dto.DashboardResponse;
import com.shaziya.attendance_system.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.shaziya.attendance_system.dto.FacultyDashboardResponse;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(
            DashboardService dashboardService
    ) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/admin")
    public ResponseEntity<DashboardResponse> getAdminDashboard() {

        return ResponseEntity.ok(
                dashboardService.getAdminDashboard()
        );
    }
    @GetMapping("/student/{studentId}")
    public ResponseEntity<StudentDashboardResponse> getStudentDashboard(
            @PathVariable Long studentId
    ) {

        return ResponseEntity.ok(
                dashboardService.getStudentDashboard(studentId)
        );
    }
    @GetMapping("/faculty/{facultyId}")
    public ResponseEntity<FacultyDashboardResponse> getFacultyDashboard(
            @PathVariable Long facultyId
    ) {

        return ResponseEntity.ok(
                dashboardService.getFacultyDashboard(facultyId)
        );
    }
}