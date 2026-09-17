package com.campuscareer.campus_career.controller;

import com.campuscareer.campus_career.dto.LoginRequest;
import com.campuscareer.campus_career.dto.LoginResponse;
import com.campuscareer.campus_career.entity.Student;
import com.campuscareer.campus_career.repository.StudentRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(
        origins = "http://localhost:63342",
        allowCredentials = "true"
)
public class AuthController {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(
            StudentRepository studentRepository,
            PasswordEncoder passwordEncoder) {

        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request,
            HttpSession session) {

        Student student = studentRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new IllegalStateException(
                                "Invalid email or password."
                        ));

        if (student.getPassword() == null
                || !passwordEncoder.matches(
                request.getPassword(),
                student.getPassword())) {

            throw new IllegalStateException(
                    "Invalid email or password."
            );
        }

        // Store logged-in user information in server session
        session.setAttribute(
                "studentId",
                student.getId()
        );

        session.setAttribute(
                "role",
                student.getRole()
        );

        return new LoginResponse(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getRole(),
                "Login successful"
        );
    }
}