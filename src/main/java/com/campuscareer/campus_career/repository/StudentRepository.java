package com.campuscareer.campus_career.repository;

import com.campuscareer.campus_career.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository
        extends JpaRepository<Student, Long> {

    Optional<Student> findByEmail(String email);
}