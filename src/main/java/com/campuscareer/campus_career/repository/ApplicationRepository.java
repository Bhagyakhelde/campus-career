package com.campuscareer.campus_career.repository;

import com.campuscareer.campus_career.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository
        extends JpaRepository<Application, Long> {

    boolean existsByStudentIdAndJobId(Long studentId, Long jobId);
}