package com.shaziya.attendance_system.service;

import com.shaziya.attendance_system.dto.CreateFacultyRequest;
import com.shaziya.attendance_system.entity.Faculty;
import com.shaziya.attendance_system.entity.Role;
import com.shaziya.attendance_system.entity.User;
import com.shaziya.attendance_system.repository.FacultyRepository;
import com.shaziya.attendance_system.repository.RoleRepository;
import com.shaziya.attendance_system.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService {

    private final UserRepository userRepository;
    private final FacultyRepository facultyRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public FacultyService(
            UserRepository userRepository,
            FacultyRepository facultyRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.facultyRepository = facultyRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Faculty createFaculty(CreateFacultyRequest request) {

        Role facultyRole = roleRepository.findByName("FACULTY")
                .orElseThrow(() ->
                        new RuntimeException("FACULTY role not found"));

        User user = new User(
                request.getName(),
                request.getEmail(),

                // 🔐 Encrypt password before saving
                passwordEncoder.encode(request.getPassword()),

                facultyRole
        );

        User savedUser = userRepository.save(user);

        Faculty faculty = new Faculty(
                request.getEmployeeId(),
                request.getDepartment(),
                request.getDesignation(),
                savedUser
        );

        return facultyRepository.save(faculty);
    }

    public List<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }
}