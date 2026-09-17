package com.campuscareer.campus_career.dto;

import com.campuscareer.campus_career.matching.JobMatchResult;

import java.util.List;

public class StudentDashboardDTO {

    private StudentResponseDTO student;
    private long totalJobs;
    private long eligibleJobs;
    private long appliedJobs;
    private List<JobMatchResult> recommendedJobs;

    public StudentDashboardDTO() {
    }

    public StudentDashboardDTO(
            StudentResponseDTO student,
            long totalJobs,
            long eligibleJobs,
            long appliedJobs,
            List<JobMatchResult> recommendedJobs) {

        this.student = student;
        this.totalJobs = totalJobs;
        this.eligibleJobs = eligibleJobs;
        this.appliedJobs = appliedJobs;
        this.recommendedJobs = recommendedJobs;
    }

    public StudentResponseDTO getStudent() {
        return student;
    }

    public void setStudent(StudentResponseDTO student) {
        this.student = student;
    }

    public long getTotalJobs() {
        return totalJobs;
    }

    public void setTotalJobs(long totalJobs) {
        this.totalJobs = totalJobs;
    }

    public long getEligibleJobs() {
        return eligibleJobs;
    }

    public void setEligibleJobs(long eligibleJobs) {
        this.eligibleJobs = eligibleJobs;
    }

    public long getAppliedJobs() {
        return appliedJobs;
    }

    public void setAppliedJobs(long appliedJobs) {
        this.appliedJobs = appliedJobs;
    }

    public List<JobMatchResult> getRecommendedJobs() {
        return recommendedJobs;
    }

    public void setRecommendedJobs(
            List<JobMatchResult> recommendedJobs) {

        this.recommendedJobs = recommendedJobs;
    }
}