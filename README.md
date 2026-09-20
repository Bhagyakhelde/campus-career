# Campus Career 🎓💼

Campus Career is a full-stack job matching platform built to help students find suitable job opportunities based on their academic qualifications and technical skills.

## 🚀 Live Demo

https://campus-career-jet.vercel.app/

## 💻 GitHub Repository

https://github.com/Bhagyakhelde/campus-career

## 📌 About the Project

Students often find it difficult to identify which job opportunities they are eligible for because every company has different requirements.

Campus Career compares a student's profile with job requirements and provides:

- Job match percentage
- Eligibility status
- Matched skills
- Missing skills
- Eligibility reason
- Application status

The platform also provides separate workflows for students and recruiters.

## ✨ Features

### 👨‍🎓 Student

- Student login
- Profile management
- View available jobs
- View detailed job requirements
- Get personalized job matching
- Check eligibility
- View matched and missing skills
- Apply for jobs
- Prevent duplicate applications
- Track application status

### 🧑‍💼 Recruiter

- Recruiter login
- Create job postings
- Edit job postings
- Delete job postings
- View candidate applications
- Review candidate details
- Update application status
- Recruiter dashboard with application statistics

## 🧠 Job Matching System

The matching engine evaluates jobs using four factors:

| Criteria | Weight |
|---|---:|
| Technical Skills | 50% |
| CGPA | 25% |
| Graduation Year | 15% |
| Backlogs | 10% |

The system also checks basic eligibility:

- CGPA must meet the job's minimum requirement
- Graduation year must match the required year
- Student must have zero backlogs

### Example

A student with:

- CGPA: 8.1
- Graduation Year: 2026
- Backlogs: 0
- Skills: Java, SQL, HTML, CSS, Spring Boot

can be compared against a job requiring:

- Minimum CGPA: 7.5
- Graduation Year: 2026
- Skills: Java, Spring Boot, SQL

The system identifies the matching skills, missing skills, eligibility and overall match percentage.

## 🛠️ Technology Stack

### Frontend

- HTML5
- CSS3
- JavaScript
- Fetch API

### Backend

- Java 21
- Spring Boot 4.0.8
- Spring MVC
- Spring Data JPA
- Hibernate
- Maven
- Spring Security

### Database

- MySQL 8.4
- Aiven MySQL

### Deployment

- Vercel — Frontend
- Render — Backend
- Aiven — Database

## 🏗️ Architecture

```text
Frontend
    ↓
REST API
    ↓
Spring Boot Controllers
    ↓
Service Layer
    ↓
Repository Layer
    ↓
JPA / Hibernate
    ↓
MySQL
