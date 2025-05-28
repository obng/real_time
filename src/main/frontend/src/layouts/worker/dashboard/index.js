import React, { useEffect, useState } from 'react';
import axios from 'axios';

// @mui material components
import Grid from '@mui/material/Grid';

// Material Dashboard 2 React components
import MDBox from 'components/MDBox';

// Material Dashboard 2 React example components
import DashboardLayout from 'examples/LayoutContainers/DashboardLayout';
import DashboardNavbar from 'examples/Navbars/DashboardNavbar';
import Footer from 'examples/Footer';

import ComplexStatisticsCard from 'examples/Cards/StatisticsCards/ComplexStatisticsCard';

function Dashboard() {
  const [jobPostings, setJobPostings] = useState([]);
  // 실제로는 로그인된 알바생의 ID를 받아와야 합니다. (예시는 1로 하드코딩)
  const workerId = 1;
  const jobPostingId = 1;

  useEffect(() => {
    axios
      .get('/api/job-postings')
      .then((response) => {
        const data = response.data;
        if (Array.isArray(data)) {
          setJobPostings(data);
        } else if (Array.isArray(data.jobPostings)) {
          setJobPostings(data.jobPostings);
        } else {
          setJobPostings([]);
        }
      })
      .catch((error) => {
        console.error('Error fetching job postings:', error);
      });
  }, []);

  return (
    <DashboardLayout>
      <DashboardNavbar />
      <MDBox py={3}>
        <Grid container spacing={3}>
          {jobPostings.map((job) =>
            job && job.id ? (
              <Grid item xs={12} md={6} lg={3} key={job.id}>
                <MDBox mb={1.5}>
                  <ComplexStatisticsCard
                    workLocation={job.workLocation}
                    jobDescription={job.jobDescription}
                    dailyWage={job.dailyWage}
                    gender={job.gender}
                    numberOfWorkers={job.numberOfWorkers}
                    createdAt={job.createdAt}
                    jobPostingId={jobPostingId}
                    workerId={workerId}
                  />
                </MDBox>
              </Grid>
            ) : null
          )}
        </Grid>
      </MDBox>
      <Footer />
    </DashboardLayout>
  );
}

export default Dashboard;
