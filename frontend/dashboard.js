// Check login
const studentId = localStorage.getItem("studentId");

if (!studentId) {
    window.location.href = "login.html";
}


// Backend URL
const API_BASE_URL =
    "https://campus-career-backend-n9s9.onrender.com";


// Dashboard API
const API_URL =
    `${API_BASE_URL}/api/students/${studentId}/dashboard`;


// Load dashboard
async function loadDashboard() {

    try {

        const response = await fetch(API_URL, {
            credentials: "include"
        });

        if (!response.ok) {
            throw new Error("Failed to load dashboard");
        }

        const data = await response.json();

        console.log("Dashboard data:", data);

        displayStudent(data.student);

        displayStatistics(data);

        displayRecommendedJobs(data.recommendedJobs);

    } catch (error) {

        console.error("Dashboard error:", error);

        document.getElementById("jobsContainer").innerHTML =
            "<p>Unable to load dashboard data.</p>";
    }
}


// Display student
function displayStudent(student) {

    if (student && student.name) {

        document.getElementById("studentName").textContent =
            student.name;

    }

}


// Display statistics
function displayStatistics(data) {

    document.getElementById("totalJobs").textContent =
        data.totalJobs ?? 0;

    document.getElementById("eligibleJobs").textContent =
        data.eligibleJobs ?? 0;

    document.getElementById("appliedJobs").textContent =
        data.appliedJobs ?? 0;

}


// Display recommended jobs
function displayRecommendedJobs(jobs) {

    const container =
        document.getElementById("jobsContainer");

    container.innerHTML = "";

    if (!jobs || jobs.length === 0) {

        container.innerHTML =
            "<p>No recommended jobs available.</p>";

        return;
    }


    jobs.forEach(job => {

        const jobCard =
            document.createElement("div");

        jobCard.className = "job-card";

        jobCard.innerHTML = `

            <div class="job-header">

                <div>

                    <div class="job-title">
                        ${job.jobTitle}
                    </div>

                    <div class="company">
                        ${job.companyName}
                    </div>

                </div>

                <div class="match">
                    ${job.matchPercentage ?? 0}% Match
                </div>

            </div>

            <div class="job-info">

                ${
            job.eligible
                ? "✅ You are eligible"
                : "❌ You are not eligible"
        }

            </div>

            <div class="skills">

                <strong>Matched Skills:</strong>
                ${job.matchedSkills || "None"}

            </div>

            <div class="skills">

                <strong>Missing Skills:</strong>
                ${job.missingSkills || "None"}

            </div>

            <button
                class="apply-btn"
                onclick="viewJob(${job.jobId})">
                View Job
            </button>

        `;

        container.appendChild(jobCard);

    });

}


// View job
function viewJob(jobId) {

    window.location.href =
        `job-details.html?id=${jobId}`;

}


// Logout
function logout() {

    localStorage.removeItem("studentId");
    localStorage.removeItem("studentName");
    localStorage.removeItem("role");

    window.location.href = "login.html";
}


// Start
loadDashboard();