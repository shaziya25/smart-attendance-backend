package com.shaziya.attendance_system.repository;

import com.shaziya.attendance_system.entity.Faculty;
import com.shaziya.attendance_system.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureRepository
        extends JpaRepository<Lecture, Long> {

    long countByFaculty(Faculty faculty);

    List<Lecture> findByFaculty(Faculty faculty);
}