package com.shaziya.attendance_system.controller;

import com.shaziya.attendance_system.dto.CreateStudentRequest;
import com.shaziya.attendance_system.dto.StudentResponse;
import com.shaziya.attendance_system.entity.Student;
import com.shaziya.attendance_system.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(
            @RequestBody CreateStudentRequest request
    ) {

        Student student = studentService.createStudent(request);

        StudentResponse response = new StudentResponse(
                student.getId(),
                student.getRollNumber(),
                student.getCourse(),
                student.getSemester(),
                student.getUser().getName(),
                student.getUser().getEmail(),
                student.getUser().getRole().getName()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents() {

        return ResponseEntity.ok(
                studentService.getAllStudents()
        );
    }
    @GetMapping("/me")
    public ResponseEntity<StudentResponse> getMyProfile() {

        return ResponseEntity.ok(
                studentService.getMyProfile()
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(
            @PathVariable Long id
    ) {

        studentService.deleteStudent(id);

        return ResponseEntity.ok("Student deleted successfully");
    }


}