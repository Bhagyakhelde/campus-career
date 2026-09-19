if (!localStorage.getItem("studentId")) {
    window.location.href = "login.html";
}

const studentId =
    localStorage.getItem("studentId");
const studentId = localStorage.getItem("studentId");

const API_URL =
    `https://campus-career-backend-n9s9.onrender.com/api/students/${studentId}/dashboard`;


// Load dashboard data
async function loadDashboard() {

    try {

        const response = await fetch(API_URL);

        if (!response.ok) {
            throw new Error("Failed to load dashboard");
        }

        const data = await response.json();

        displayStudent(data.student);

        displayStatistics(data);

        displayRecommendedJobs(data.recommendedJobs);

    } catch (error) {

        console.error(error);

        document.getElementById("jobsContainer").innerHTML =
            "<p>Unable to load dashboard. Make sure Spring Boot is running.</p>";
    }
}


// Display student information
function displayStudent(student) {

    document.getElementById("studentName").textContent =
        student.name;
}


// Display dashboard statistics
function displayStatistics(data) {

    document.getElementById("totalJobs").textContent =
        data.totalJobs;

    document.getElementById("eligibleJobs").textContent =
        data.eligibleJobs;

    document.getElementById("appliedJobs").textContent =
        data.appliedJobs;
}


// Display recommended jobs
function displayRecommendedJobs(jobs) {

    const container =
        document.getElementById("jobsContainer");

    container.innerHTML = "";

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
                    ${job.matchPercentage}% Match
                </div>

            </div>


            <div class="job-info">

                ${job.eligible
            ? "✅ You are eligible"
            : "❌ You are not eligible"}

            </div>


            <div class="skills">

                <strong>Matched Skills:</strong>
                ${job.matchedSkills || "None"}

            </div>


            <div class="skills">

                <strong>Missing Skills:</strong>
                ${job.missingSkills || "None"}

            </div>


            <button class="apply-btn"
                    onclick="viewJob(${job.jobId})">
                View Job
            </button>

        `;

        container.appendChild(jobCard);

    });
}


// View job details
function viewJob(jobId) {

    window.location.href =
        `job-details.html?id=${jobId}`;

}

function logout() {

    localStorage.removeItem("studentId");
    localStorage.removeItem("studentName");

    window.location.href = "login.html";
}

// Start dashboard
loadDashboard();