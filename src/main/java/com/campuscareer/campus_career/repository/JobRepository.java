package com.campuscareer.campus_career.repository;

import com.campuscareer.campus_career.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {

}