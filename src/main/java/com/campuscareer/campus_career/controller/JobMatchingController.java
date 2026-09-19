package com.campuscareer.campus_career.controller;

import com.campuscareer.campus_career.entity.Job;
import com.campuscareer.campus_career.entity.Student;
import com.campuscareer.campus_career.matching.JobMatchResult;
import com.campuscareer.campus_career.matching.JobMatchingService;
import com.campuscareer.campus_career.service.JobService;
import com.campuscareer.campus_career.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@CrossOrigin(origins = "https://campus-career-jet.vercel.app")
@RestController
@RequestMapping("/api/matching")
public class JobMatchingController {

    private final JobMatchingService jobMatchingService;
    private final StudentService studentService;
    private final JobService jobService;

    public JobMatchingController(
            JobMatchingService jobMatchingService,
            StudentService studentService,
            JobService jobService) {

        this.jobMatchingService = jobMatchingService;
        this.studentService = studentService;
        this.jobService = jobService;
    }

    // Get matching jobs for a student
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<JobMatchResult>> getMatchingJobs(
            @PathVariable Long studentId) {

        Student student = studentService
                .getStudentById(studentId)
                .orElse(null);

        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        List<JobMatchResult> results = jobService
                .getAllJobs()
                .stream()
                .map(job -> jobMatchingService
                        .matchStudentToJob(student, job))
                .sorted(Comparator
                        .comparingDouble(
                                JobMatchResult::getMatchPercentage)
                        .reversed())
                .toList();

        return ResponseEntity.ok(results);
    }

    // Match one student with one job
    @GetMapping("/student/{studentId}/job/{jobId}")
    public ResponseEntity<JobMatchResult> matchOneJob(
            @PathVariable Long studentId,
            @PathVariable Long jobId) {

        Student student = studentService
                .getStudentById(studentId)
                .orElse(null);

        Job job = jobService
                .getJobById(jobId)
                .orElse(null);

        if (student == null || job == null) {
            return ResponseEntity.notFound().build();
        }

        JobMatchResult result =
                jobMatchingService.matchStudentToJob(
                        student,
                        job
                );

        return ResponseEntity.ok(result);
    }
}