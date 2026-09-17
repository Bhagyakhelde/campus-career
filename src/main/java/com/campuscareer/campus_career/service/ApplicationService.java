package com.campuscareer.campus_career.service;

import com.campuscareer.campus_career.entity.Application;
import com.campuscareer.campus_career.entity.Job;
import com.campuscareer.campus_career.entity.Student;
import com.campuscareer.campus_career.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationService(
            ApplicationRepository applicationRepository) {

        this.applicationRepository = applicationRepository;
    }

    public Application createApplication(Application application) {

        Student student = application.getStudent();
        Job job = application.getJob();

        Long studentId = student.getId();
        Long jobId = job.getId();

        // 1. Check duplicate application
        if (applicationRepository
                .existsByStudentIdAndJobId(studentId, jobId)) {

            throw new IllegalStateException(
                    "You have already applied for this job."
            );
        }

        // 2. Check CGPA eligibility
        if (student.getCgpa() == null
                || job.getMinimumCgpa() == null
                || student.getCgpa() < job.getMinimumCgpa()) {

            throw new IllegalStateException(
                    "You are not eligible for this job because your CGPA does not meet the requirement."
            );
        }

        // 3. Check graduation year
        if (student.getGraduationYear() == null
                || job.getGraduationYear() == null
                || !student.getGraduationYear()
                .equals(job.getGraduationYear())) {

            throw new IllegalStateException(
                    "You are not eligible for this job because your graduation year does not match."
            );
        }

        // 4. Check backlogs
        if (student.getBacklogs() == null
                || student.getBacklogs() > 0) {

            throw new IllegalStateException(
                    "You are not eligible for this job because you have active backlogs."
            );
        }

        // 5. Everything is valid
        return applicationRepository.save(application);
    }

    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    public Optional<Application> getApplicationById(Long id) {
        return applicationRepository.findById(id);
    }

    public Application updateApplication(Application application) {
        return applicationRepository.save(application);
    }

    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }
}