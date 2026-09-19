package com.campuscareer.campus_career.controller;

import com.campuscareer.campus_career.dto.StudentDashboardDTO;
import com.campuscareer.campus_career.dto.StudentResponseDTO;
import com.campuscareer.campus_career.entity.Student;
import com.campuscareer.campus_career.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(
        origins = "https://campus-career-jet.vercel.app",
        allowCredentials = "true"
)
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public StudentResponseDTO createStudent(@RequestBody Student student) {

        Student savedStudent = studentService.createStudent(student);

        return convertToDTO(savedStudent);
    }

    @GetMapping
    public List<StudentResponseDTO> getAllStudents() {

        return studentService.getAllStudents()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(
            @PathVariable Long id) {

        return studentService.getStudentById(id)
                .map(student ->
                        ResponseEntity.ok(convertToDTO(student)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(
            @PathVariable Long id,
            @RequestBody Student student) {

        return studentService.getStudentById(id)
                .map(existingStudent -> {

                    Student updatedStudent =
                            studentService.updateStudent(
                                    existingStudent,
                                    student
                            );

                    return ResponseEntity.ok(
                            convertToDTO(updatedStudent)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(
            @PathVariable Long id) {

        if (studentService.getStudentById(id).isPresent()) {

            studentService.deleteStudent(id);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    // Student Dashboard
    @GetMapping("/{id}/dashboard")
    public ResponseEntity<StudentDashboardDTO> getStudentDashboard(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                studentService.getStudentDashboard(id)
        );
    }

    private StudentResponseDTO convertToDTO(Student student) {

        return new StudentResponseDTO(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getCgpa(),
                student.getGraduationYear(),
                student.getBacklogs(),
                student.getSkills()
        );
    }
}