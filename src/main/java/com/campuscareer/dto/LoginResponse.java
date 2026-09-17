package com.campuscareer.campus_career.dto;

public class LoginResponse {

    private Long studentId;
    private String name;
    private String email;
    private String role;
    private String message;

    public LoginResponse() {
    }

    public LoginResponse(
            Long studentId,
            String name,
            String email,
            String role,
            String message) {

        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.role = role;
        this.message = message;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}