package com.campuscareer.campus_career.matching;

import com.campuscareer.campus_career.entity.Job;
import com.campuscareer.campus_career.entity.Student;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class JobMatchingService {

    public JobMatchResult matchStudentToJob(
            Student student,
            Job job) {

        // Check that student and job are available
        if (student == null || job == null) {
            throw new IllegalArgumentException(
                    "Student and job cannot be null"
            );
        }

        JobMatchResult result = new JobMatchResult();

        result.setJobId(job.getId());
        result.setJobTitle(job.getJobTitle());
        result.setCompanyName(job.getCompanyName());

        StringBuilder reason = new StringBuilder();

        // =====================================================
        // 1. SKILLS SCORE - 50%
        // =====================================================

        double skillMatchPercentage = 0;

        StringBuilder missingSkills =
                new StringBuilder();

        StringBuilder matchedSkills =
                new StringBuilder();

        if (student.getSkills() != null
                && job.getSkills() != null
                && !job.getSkills().trim().isEmpty()) {

            List<String> studentSkills =
                    Arrays.stream(
                                    student.getSkills()
                                            .toLowerCase()
                                            .split(",")
                            )
                            .map(String::trim)
                            .filter(skill -> !skill.isEmpty())
                            .toList();

            String[] requiredSkills =
                    job.getSkills()
                            .toLowerCase()
                            .split(",");

            int matchedSkillCount = 0;

            for (String skill : requiredSkills) {

                String requiredSkill =
                        skill.trim();

                if (requiredSkill.isEmpty()) {
                    continue;
                }

                if (studentSkills.contains(requiredSkill)) {

                    matchedSkillCount++;

                    if (matchedSkills.length() > 0) {
                        matchedSkills.append(", ");
                    }

                    matchedSkills.append(requiredSkill);

                } else {

                    if (missingSkills.length() > 0) {
                        missingSkills.append(", ");
                    }

                    missingSkills.append(requiredSkill);
                }
            }

            int totalRequiredSkills = 0;

            for (String skill : requiredSkills) {

                if (!skill.trim().isEmpty()) {
                    totalRequiredSkills++;
                }
            }

            if (totalRequiredSkills > 0) {

                skillMatchPercentage =
                        (matchedSkillCount * 100.0)
                                / totalRequiredSkills;
            }

            if (matchedSkillCount == totalRequiredSkills) {

                reason.append(
                        "All required skills matched. "
                );

            } else {

                reason.append(
                        "Some required skills are missing. "
                );
            }

        } else {

            missingSkills.append(
                    "Skills information unavailable."
            );

            reason.append(
                    "Skills information unavailable. "
            );
        }

        result.setMatchedSkills(
                matchedSkills.toString()
        );

        result.setMissingSkills(
                missingSkills.toString()
        );


        // =====================================================
        // 2. CGPA SCORE - 25%
        // =====================================================

        double cgpaScore = 0;

        if (student.getCgpa() != null
                && job.getMinimumCgpa() != null) {

            if (student.getCgpa()
                    >= job.getMinimumCgpa()) {

                double difference =
                        student.getCgpa()
                                - job.getMinimumCgpa();

                /*
                 * Student meets the minimum CGPA.
                 *
                 * 5 points  -> requirement met
                 * 15 points -> 0.5+ above requirement
                 * 25 points -> 1.0+ above requirement
                 */

                if (difference >= 1.0) {

                    cgpaScore = 25;

                } else if (difference >= 0.5) {

                    cgpaScore = 15;

                } else {

                    cgpaScore = 5;
                }

                reason.append(
                                "CGPA requirement met. You are "
                        )
                        .append(
                                String.format(
                                        "%.1f",
                                        difference
                                )
                        )
                        .append(
                                " points above the required CGPA. "
                        );

            } else {

                reason.append(
                        "CGPA requirement not met. "
                );
            }

        } else {

            reason.append(
                    "CGPA information unavailable. "
            );
        }


        // =====================================================
        // 3. GRADUATION YEAR SCORE - 15%
        // =====================================================

        double graduationScore = 0;

        if (student.getGraduationYear() != null
                && job.getGraduationYear() != null) {

            if (student.getGraduationYear()
                    .equals(job.getGraduationYear())) {

                graduationScore = 15;

                reason.append(
                        "Graduation year matched. "
                );

            } else {

                reason.append(
                        "Graduation year does not match. "
                );
            }

        } else {

            reason.append(
                    "Graduation year information unavailable. "
            );
        }


        // =====================================================
        // 4. BACKLOG SCORE - 10%
        // =====================================================

        double backlogScore = 0;

        if (student.getBacklogs() != null) {

            if (student.getBacklogs() == 0) {

                backlogScore = 10;

                reason.append(
                        "No active backlogs. "
                );

            } else {

                reason.append(
                        "Has active backlogs. "
                );
            }

        } else {

            reason.append(
                    "Backlog information unavailable. "
            );
        }


        // =====================================================
        // 5. FINAL MATCH SCORE
        // =====================================================

        double skillScore =
                skillMatchPercentage * 0.50;

        double finalMatchPercentage =
                skillScore
                        + cgpaScore
                        + graduationScore
                        + backlogScore;

        // Round to 2 decimal places
        finalMatchPercentage =
                Math.round(
                        finalMatchPercentage * 100.0
                ) / 100.0;

        result.setMatchPercentage(
                finalMatchPercentage
        );


        // =====================================================
        // 6. ELIGIBILITY
        // =====================================================

        boolean eligible =
                student.getCgpa() != null
                        && job.getMinimumCgpa() != null
                        && student.getGraduationYear() != null
                        && job.getGraduationYear() != null
                        && student.getBacklogs() != null

                        && student.getCgpa()
                        >= job.getMinimumCgpa()

                        && student.getGraduationYear()
                        .equals(
                                job.getGraduationYear()
                        )

                        && student.getBacklogs() == 0;

        result.setEligible(eligible);


        // =====================================================
        // 7. FINAL EXPLANATION
        // =====================================================

        result.setReason(
                reason.toString().trim()
        );

        return result;
    }
}