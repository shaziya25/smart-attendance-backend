package com.shaziya.attendance_system.controller;

import com.shaziya.attendance_system.dto.CreateLectureRequest;
import com.shaziya.attendance_system.entity.Lecture;
import com.shaziya.attendance_system.service.LectureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lectures")
public class LectureController {

    private final LectureService lectureService;

    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @PostMapping
    public ResponseEntity<Lecture> createLecture(
            @RequestBody CreateLectureRequest request
    ) {
        return ResponseEntity.ok(
                lectureService.createLecture(request)
        );
    }

    @GetMapping
    public ResponseEntity<List<Lecture>> getAllLectures() {
        return ResponseEntity.ok(
                lectureService.getAllLectures()
        );
    }
    @GetMapping("/my-lectures")
    public ResponseEntity<List<Lecture>> getMyLectures() {
        System.out.println("MY LECTURES API HIT");
        return ResponseEntity.ok(
                lectureService.getMyLectures()
        );
    }
}