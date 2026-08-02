package com.shaziya.attendance_system.service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.shaziya.attendance_system.dto.CreateLectureRequest;
import com.shaziya.attendance_system.entity.Faculty;
import com.shaziya.attendance_system.entity.Lecture;
import com.shaziya.attendance_system.repository.FacultyRepository;
import com.shaziya.attendance_system.repository.LectureRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectureService {

    private final LectureRepository lectureRepository;
    private final FacultyRepository facultyRepository;

    public LectureService(
            LectureRepository lectureRepository,
            FacultyRepository facultyRepository
    ) {
        this.lectureRepository = lectureRepository;
        this.facultyRepository = facultyRepository;
    }

    public Lecture createLecture(CreateLectureRequest request) {

        Faculty faculty = facultyRepository.findById(request.getFacultyId())
                .orElseThrow(() ->
                        new RuntimeException("Faculty not found"));

        Lecture lecture = new Lecture(
                request.getSubject(),
                request.getRoom(),
                request.getStartTime(),
                request.getEndTime(),
                faculty
        );

        return lectureRepository.save(lecture);
    }

    public List<Lecture> getAllLectures() {
        return lectureRepository.findAll();
    }
    public List<Lecture> getMyLectures() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email = authentication.getName();

        System.out.println("JWT EMAIL: " + email);

        Faculty faculty = facultyRepository
                .findByUserEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Faculty not found"));

        System.out.println("FACULTY ID: " + faculty.getId());

        return lectureRepository.findByFaculty(faculty);
    }
}