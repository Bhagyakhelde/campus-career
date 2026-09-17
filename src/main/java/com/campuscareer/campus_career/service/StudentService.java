package com.campuscareer.campus_career.service;

import com.campuscareer.campus_career.dto.StudentDashboardDTO;
import com.campuscareer.campus_career.dto.StudentResponseDTO;
import com.campuscareer.campus_career.entity.Student;
import com.campuscareer.campus_career.matching.JobMatchResult;
import com.campuscareer.campus_career.matching.JobMatchingService;
import com.campuscareer.campus_career.repository.ApplicationRepository;
import com.campuscareer.campus_career.repository.JobRepository;
import com.campuscareer.campus_career.repository.StudentRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final JobRepository jobRepository;
    private final ApplicationRepository applicationRepository;
    private final JobMatchingService jobMatchingService;

    public StudentService(
            StudentRepository studentRepository,
            PasswordEncoder passwordEncoder,
            JobRepository jobRepository,
            ApplicationRepository applicationRepository,
            JobMatchingService jobMatchingService) {

        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
        this.jobRepository = jobRepository;
        this.applicationRepository = applicationRepository;
        this.jobMatchingService = jobMatchingService;
    }

    public Student createStudent(Student student) {

        if (student.getPassword() != null
                && !student.getPassword().isBlank()) {

            student.setPassword(
                    passwordEncoder.encode(student.getPassword())
            );
        }

        return studentRepository.save(student);
    }

    public Student updateStudent(
            Student existingStudent,
            Student updatedStudent) {

        existingStudent.setName(updatedStudent.getName());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setCgpa(updatedStudent.getCgpa());
        existingStudent.setGraduationYear(
                updatedStudent.getGraduationYear()
        );
        existingStudent.setBacklogs(updatedStudent.getBacklogs());
        existingStudent.setSkills(updatedStudent.getSkills());

        if (updatedStudent.getPassword() != null
                && !updatedStudent.getPassword().isBlank()) {

            existingStudent.setPassword(
                    passwordEncoder.encode(
                            updatedStudent.getPassword()
                    )
            );
        }

        return studentRepository.save(existingStudent);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    // Student dashboard
    public StudentDashboardDTO getStudentDashboard(Long studentId) {

        Student student = studentRepository
                .findById(studentId)
                .orElseThrow(() ->
                        new IllegalStateException(
                                "Student not found."
                        )
                );

        long totalJobs = jobRepository.count();

        long eligibleJobs = jobRepository.findAll()
                .stream()
                .filter(job ->
                        student.getCgpa() != null
                                && job.getMinimumCgpa() != null
                                && student.getCgpa()
                                >= job.getMinimumCgpa()
                                && student.getGraduationYear() != null
                                && job.getGraduationYear() != null
                                && student.getGraduationYear()
                                .equals(job.getGraduationYear())
                                && student.getBacklogs() != null
                                && student.getBacklogs() == 0
                )
                .count();

        long appliedJobs = applicationRepository
                .findAll()
                .stream()
                .filter(application ->
                        application.getStudent()
                                .getId()
                                .equals(studentId)
                )
                .count();

        // Generate recommended jobs using the matching algorithm
        List<JobMatchResult> recommendedJobs =
                jobRepository.findAll()
                        .stream()
                        .map(job ->
                                jobMatchingService.matchStudentToJob(
                                        student,
                                        job
                                ))
                        .sorted(
                                Comparator.comparingDouble(
                                        JobMatchResult::getMatchPercentage
                                ).reversed()
                        )
                        .toList();

        StudentResponseDTO studentDTO =
                new StudentResponseDTO(
                        student.getId(),
                        student.getName(),
                        student.getEmail(),
                        student.getCgpa(),
                        student.getGraduationYear(),
                        student.getBacklogs(),
                        student.getSkills()
                );

        return new StudentDashboardDTO(
                studentDTO,
                totalJobs,
                eligibleJobs,
                appliedJobs,
                recommendedJobs
        );
    }
}