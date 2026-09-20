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

🔐 Authentication & Security
BCrypt password hashing
Session-based authentication
Student and recruiter roles
Role-based recruiter actions
CORS configuration
Database credentials managed through environment variables
📂 Project Structure
campus-career/
│
├── frontend/
│   ├── index.html
│   ├── login.html
│   ├── dashboard.html
│   ├── jobs.html
│   ├── job-details.html
│   ├── applications.html
│   ├── profile.html
│   ├── recruiter-dashboard.html
│   ├── manage-jobs.html
│   ├── add-job.html
│   ├── edit-job.html
│   ├── script.js
│   └── style.css
│
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── campuscareer/
│                   └── campus_career/
│                       ├── config/
│                       ├── controller/
│                       ├── dto/
│                       ├── entity/
│                       ├── exception/
│                       ├── matching/
│                       ├── repository/
│                       └── service/
│
├── Dockerfile
├── pom.xml
└── mvnw
🔌 Major REST APIs
Authentication
POST /api/auth/login
Jobs
GET    /api/jobs
GET    /api/jobs/{id}
POST   /api/jobs
PUT    /api/jobs/{id}
DELETE /api/jobs/{id}
Matching
GET /api/matching/student/{studentId}
Applications
POST /api/applications
GET /api/applications
GET /api/applications/{id}
PUT /api/applications/{id}/status
DELETE /api/applications/{id}
Students
GET /api/students/{id}
GET /api/students/{id}/dashboard
☁️ Deployment

The project is deployed using:

GitHub
   │
   ├── Frontend → Vercel
   │
   └── Backend → Render
                    │
                    ↓
                Aiven MySQL
🎯 Future Improvements
Resume parsing and skill extraction
Email notifications for application updates
Advanced job recommendations
Recruiter search and filtering
Admin management
JWT-based authentication
Automated deployment pipeline
👩‍💻 Author

Bhagyashree S Khelde

B.Tech — Electronics & Communication Engineering
2026 Graduate

GitHub: https://github.com/Bhagyakhelde
