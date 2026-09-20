# 🎓 Campus Career

A full-stack job matching and application platform that helps students find suitable job opportunities based on their skills, CGPA, graduation year, and eligibility.

🔗 **Live Demo:** https://campus-career-jet.vercel.app/

🔗 **GitHub:** https://github.com/Bhagyakhelde/campus-career

---

## ✨ Features

### 👨‍🎓 Student

- Student login
- Profile management
- Browse job opportunities
- Job eligibility checking
- Match percentage calculation
- Matched and missing skills
- Apply for jobs
- Track application status
- Duplicate application prevention

### 👨‍💼 Recruiter

- Recruiter login
- Recruiter dashboard
- Create jobs
- Edit jobs
- Delete jobs
- View candidate applications
- Update application status

---

## 🎯 Job Matching System

The matching system calculates a job match percentage using:

| Criteria | Weight |
|---|---:|
| Skills | 50% |
| CGPA | 25% |
| Graduation Year | 15% |
| Backlogs | 10% |

The system also shows:

- ✅ Matched skills
- ❌ Missing skills
- 📊 Match percentage
- 📝 Eligibility reason

---

## 🔐 Authentication & Security

- BCrypt password hashing
- Session-based authentication
- Student and recruiter roles
- Role-based recruiter actions
- CORS configuration
- Database credentials managed through environment variables

---

## 🛠️ Tech Stack

**Frontend**
- HTML
- CSS
- JavaScript

**Backend**
- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- Maven

**Database**
- MySQL

**Deployment**
- Vercel
- Render
- Aiven MySQL

---

## 🏗️ Architecture

```text
Frontend
   ↓
REST APIs
   ↓
Spring Boot Controllers
   ↓
Services
   ↓
Repositories / JPA
   ↓
MySQL Database
```

---

## 📂 Project Structure

```text
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
│   └── js / css
│
├── src/
│   └── main/
│       ├── java/
│       │   └── com/campuscareer/campus_career/
│       │       ├── config/
│       │       ├── controller/
│       │       ├── dto/
│       │       ├── entity/
│       │       ├── exception/
│       │       ├── matching/
│       │       ├── repository/
│       │       └── service/
│       │
│       └── resources/
│           └── application.properties
│
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

---

## 🔗 REST APIs

### Authentication

```text
POST /api/auth/login
```

### Jobs

```text
GET    /api/jobs
GET    /api/jobs/{id}
POST   /api/jobs
PUT    /api/jobs/{id}
DELETE /api/jobs/{id}
```

### Job Matching

```text
GET /api/matching/student/{studentId}
```

### Applications

```text
POST   /api/applications
GET    /api/applications
GET    /api/applications/{id}
PUT    /api/applications/{id}/status
DELETE /api/applications/{id}
```

---

## 🚀 Deployment

**Frontend:** Vercel  
https://campus-career-jet.vercel.app/

**Backend:** Render  
https://campus-career-backend-n9s9.onrender.com

**Database:** Aiven MySQL

Database credentials are stored using environment variables.

---

## 🔮 Future Improvements

- Resume upload and parsing
- Email notifications
- AI-based job recommendations
- Advanced candidate filtering
- Admin dashboard
- Interview scheduling

---

## 👩‍💻 Author

**Bhagyashree S Khelde**

🔗 GitHub: https://github.com/Bhagyakhelde  
🔗 LinkedIn: https://www.linkedin.com/in/bhagyakhelde/
