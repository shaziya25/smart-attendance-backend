package com.shaziya.attendance_system.repository;

import com.shaziya.attendance_system.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FacultyRepository
        extends JpaRepository<Faculty, Long> {

    Optional<Faculty> findByUserEmail(String email);
}