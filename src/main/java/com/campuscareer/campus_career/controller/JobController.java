package com.campuscareer.campus_career.controller;

import com.campuscareer.campus_career.entity.Job;
import com.campuscareer.campus_career.service.JobService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(
        origins = "http://localhost:63342",
        allowCredentials = "true"
)
@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }


    // ADD JOB - RECRUITER ONLY
    @PostMapping
    public ResponseEntity<?> addJob(
            @RequestBody Job job,
            HttpSession session) {

        if (!isRecruiter(session)) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Recruiter access required.");
        }

        return ResponseEntity.ok(
                jobService.createJob(job)
        );
    }


    // GET ALL JOBS - STUDENTS AND RECRUITERS
    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }


    // GET ONE JOB - STUDENTS AND RECRUITERS
    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(
            @PathVariable Long id) {

        return jobService.getJobById(id)
                .map(ResponseEntity::ok)
                .orElse(
                        ResponseEntity.notFound().build()
                );
    }


    // UPDATE JOB - RECRUITER ONLY
    @PutMapping("/{id}")
    public ResponseEntity<?> updateJob(
            @PathVariable Long id,
            @RequestBody Job job,
            HttpSession session) {

        if (!isRecruiter(session)) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Recruiter access required.");
        }

        return jobService.getJobById(id)
                .map(existingJob -> {

                    existingJob.setCompanyName(
                            job.getCompanyName()
                    );

                    existingJob.setJobTitle(
                            job.getJobTitle()
                    );

                    existingJob.setLocation(
                            job.getLocation()
                    );

                    existingJob.setMinimumCgpa(
                            job.getMinimumCgpa()
                    );

                    existingJob.setGraduationYear(
                            job.getGraduationYear()
                    );

                    existingJob.setSkills(
                            job.getSkills()
                    );

                    existingJob.setDescription(
                            job.getDescription()
                    );

                    return ResponseEntity.ok(
                            jobService.updateJob(existingJob)
                    );
                })
                .orElse(
                        ResponseEntity.notFound().build()
                );
    }


    // DELETE JOB - RECRUITER ONLY
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJob(
            @PathVariable Long id,
            HttpSession session) {

        if (!isRecruiter(session)) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Recruiter access required.");
        }

        if (jobService.getJobById(id).isPresent()) {

            jobService.deleteJob(id);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }


    // Check whether logged-in user is a recruiter
    private boolean isRecruiter(HttpSession session) {

        Object role =
                session.getAttribute("role");

        return role != null
                && role.toString()
                .equals("RECRUITER");
    }
}