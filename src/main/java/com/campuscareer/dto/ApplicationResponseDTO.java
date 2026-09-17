package com.campuscareer.dto;

public class ApplicationResponseDTO {

    private Long id;

    private Long studentId;
    private String studentName;
    private String studentEmail;

    private Long jobId;
    private String jobTitle;
    private String companyName;

    private String status;

    public ApplicationResponseDTO() {
    }

    public ApplicationResponseDTO(
            Long id,
            Long studentId,
            String studentName,
            String studentEmail,
            Long jobId,
            String jobTitle,
            String companyName,
            String status) {

        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}