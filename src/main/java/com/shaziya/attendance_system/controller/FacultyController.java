package com.shaziya.attendance_system.controller;

import com.shaziya.attendance_system.dto.CreateFacultyRequest;
import com.shaziya.attendance_system.entity.Faculty;
import com.shaziya.attendance_system.service.FacultyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public ResponseEntity<Faculty> createFaculty(
            @RequestBody CreateFacultyRequest request
    ) {
        Faculty faculty = facultyService.createFaculty(request);

        return ResponseEntity.ok(faculty);
    }

    @GetMapping
    public ResponseEntity<List<Faculty>> getAllFaculty() {

        return ResponseEntity.ok(
                facultyService.getAllFaculty()
        );
    }
}