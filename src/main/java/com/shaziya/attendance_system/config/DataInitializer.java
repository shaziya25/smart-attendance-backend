package com.shaziya.attendance_system.config;

import com.shaziya.attendance_system.entity.Role;
import com.shaziya.attendance_system.entity.User;
import com.shaziya.attendance_system.repository.RoleRepository;
import com.shaziya.attendance_system.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initializeData(
            RoleRepository roleRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {

            if (userRepository.findByEmail("admin@college.com").isEmpty()) {

                Role adminRole = roleRepository.findByName("ADMIN")
                        .orElseThrow(() ->
                                new RuntimeException("ADMIN role not found"));

                User admin = new User(
                        "Admin",
                        "admin@college.com",
                        passwordEncoder.encode("admin123"),
                        adminRole
                );

                userRepository.save(admin);

                System.out.println("ADMIN CREATED SUCCESSFULLY");
            }
        };
    }
}