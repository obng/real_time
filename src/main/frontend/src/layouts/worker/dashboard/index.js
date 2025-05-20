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
          {jobPostings.map((job) => (
            <Grid item xs={12} md={6} lg={3} key={job.id}>
              <MDBox mb={1.5}>
                <ComplexStatisticsCard
                  workLocation={job.workLocation}
                  jobDescription={job.jobDescription}
                  dailyWage={job.dailyWage}
                  gender={job.gender}
                  numberOfWorkers={job.numberOfWorkers}
                />
              </MDBox>
            </Grid>
          ))}
        </Grid>
      </MDBox>
      <Footer />
    </DashboardLayout>
  );
}

export default Dashboard;
