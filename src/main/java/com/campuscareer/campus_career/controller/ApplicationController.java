package com.campuscareer.campus_career.controller;

import com.campuscareer.dto.ApplicationResponseDTO;
import com.campuscareer.campus_career.entity.Application;
import com.campuscareer.campus_career.entity.ApplicationStatus;
import com.campuscareer.campus_career.entity.Job;
import com.campuscareer.campus_career.entity.Student;
import com.campuscareer.campus_career.service.ApplicationService;
import com.campuscareer.campus_career.service.JobService;
import com.campuscareer.campus_career.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(
        origins = "https://campus-career-jet.vercel.app",
        allowCredentials = "true"
)
public class ApplicationController {

    private final ApplicationService applicationService;
    private final StudentService studentService;
    private final JobService jobService;

    public ApplicationController(
            ApplicationService applicationService,
            StudentService studentService,
            JobService jobService) {

        this.applicationService = applicationService;
        this.studentService = studentService;
        this.jobService = jobService;
    }

    // Apply for a job
    @PostMapping("/student/{studentId}/job/{jobId}")
    public ResponseEntity<ApplicationResponseDTO> applyForJob(
            @PathVariable Long studentId,
            @PathVariable Long jobId) {

        Student student =
                studentService.getStudentById(studentId)
                        .orElseThrow(() ->
                                new IllegalStateException(
                                        "Student not found."
                                ));

        Job job =
                jobService.getJobById(jobId)
                        .orElseThrow(() ->
                                new IllegalStateException(
                                        "Job not found."
                                ));

        Application application = new Application();

        application.setStudent(student);
        application.setJob(job);
        application.setStatus(ApplicationStatus.APPLIED);

        Application savedApplication =
                applicationService.createApplication(application);

        return ResponseEntity.ok(
                convertToDTO(savedApplication)
        );
    }

    // Get all applications
    @GetMapping
    public List<ApplicationResponseDTO> getAllApplications() {

        return applicationService
                .getAllApplications()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get application by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApplicationResponseDTO> getApplicationById(
            @PathVariable Long id) {

        return applicationService
                .getApplicationById(id)
                .map(application ->
                        ResponseEntity.ok(
                                convertToDTO(application)
                        )
                )
                .orElseGet(() ->
                        ResponseEntity.notFound().build()
                );
    }

    // Update application status - RECRUITER ONLY
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(
            @PathVariable Long id,
            @RequestParam ApplicationStatus status,
            HttpSession session) {

        if (!isRecruiter(session)) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Recruiter access required.");
        }

        Application application =
                applicationService
                        .getApplicationById(id)
                        .orElseThrow(() ->
                                new IllegalStateException(
                                        "Application not found."
                                ));

        application.setStatus(status);

        Application updatedApplication =
                applicationService
                        .updateApplication(application);

        return ResponseEntity.ok(
                convertToDTO(updatedApplication)
        );
    }

    // Delete application - RECRUITER ONLY
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteApplication(
            @PathVariable Long id,
            HttpSession session) {

        if (!isRecruiter(session)) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Recruiter access required.");
        }

        applicationService.deleteApplication(id);

        return ResponseEntity.noContent().build();
    }

    // Convert Application entity to safe DTO
    private ApplicationResponseDTO convertToDTO(
            Application application) {

        Student student = application.getStudent();
        Job job = application.getJob();

        return new ApplicationResponseDTO(
                application.getId(),

                student.getId(),
                student.getName(),
                student.getEmail(),

                job.getId(),
                job.getJobTitle(),
                job.getCompanyName(),

                application.getStatus().toString()
        );
    }

    // Check whether logged-in user is a recruiter
    private boolean isRecruiter(HttpSession session) {

        Object role =
                session.getAttribute("role");

        return role != null
                && role.toString().equals("RECRUITER");
    }
}