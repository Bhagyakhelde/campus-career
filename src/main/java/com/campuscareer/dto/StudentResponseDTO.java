package com.campuscareer.campus_career.dto;

public class StudentResponseDTO {

    private Long id;
    private String name;
    private String email;
    private Double cgpa;
    private Integer graduationYear;
    private Integer backlogs;
    private String skills;

    public StudentResponseDTO() {
    }

    public StudentResponseDTO(
            Long id,
            String name,
            String email,
            Double cgpa,
            Integer graduationYear,
            Integer backlogs,
            String skills) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.cgpa = cgpa;
        this.graduationYear = graduationYear;
        this.backlogs = backlogs;
        this.skills = skills;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getCgpa() {
        return cgpa;
    }

    public void setCgpa(Double cgpa) {
        this.cgpa = cgpa;
    }

    public Integer getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(Integer graduationYear) {
        this.graduationYear = graduationYear;
    }

    public Integer getBacklogs() {
        return backlogs;
    }

    public void setBacklogs(Integer backlogs) {
        this.backlogs = backlogs;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}