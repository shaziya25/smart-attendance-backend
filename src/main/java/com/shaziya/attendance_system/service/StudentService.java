package com.shaziya.attendance_system.service;
import com.shaziya.attendance_system.dto.StudentResponse;
import com.shaziya.attendance_system.dto.CreateStudentRequest;
import com.shaziya.attendance_system.entity.Role;
import com.shaziya.attendance_system.entity.Student;
import com.shaziya.attendance_system.entity.User;
import com.shaziya.attendance_system.repository.RoleRepository;
import com.shaziya.attendance_system.repository.StudentRepository;
import com.shaziya.attendance_system.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@Service
public class StudentService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public StudentService(
            UserRepository userRepository,
            StudentRepository studentRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Student createStudent(CreateStudentRequest request) {

        Role studentRole = roleRepository.findByName("STUDENT")
                .orElseThrow(() ->
                        new RuntimeException("STUDENT role not found"));

        User user = new User(
                request.getName(),
                request.getEmail(),

                // 🔐 Encrypt password before saving
                passwordEncoder.encode(request.getPassword()),

                studentRole
        );

        User savedUser = userRepository.save(user);

        Student student = new Student(
                request.getRollNumber(),
                request.getCourse(),
                request.getSemester(),
                savedUser
        );

        return studentRepository.save(student);
    }

    public List<StudentResponse> getAllStudents() {

        return studentRepository.findAll()
                .stream()
                .map(student -> new StudentResponse(
                        student.getId(),
                        student.getRollNumber(),
                        student.getCourse(),
                        student.getSemester(),
                        student.getUser().getName(),
                        student.getUser().getEmail(),
                        student.getUser().getRole().getName()
                ))
                .toList();
    }
    public void deleteStudent(Long id) {

        studentRepository.deleteById(id);

    }
    public StudentResponse getMyProfile() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email = authentication.getName();

        Student student = studentRepository
                .findByUserEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));

        return new StudentResponse(
                student.getId(),
                student.getRollNumber(),
                student.getCourse(),
                student.getSemester(),
                student.getUser().getName(),
                student.getUser().getEmail(),
                student.getUser().getRole().getName()
        );
    }
}