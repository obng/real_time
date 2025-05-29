import Grid from '@mui/material/Grid';
import Divider from '@mui/material/Divider';
import MDBox from 'components/MDBox';
import MDTypography from 'components/MDTypography';
import DashboardLayout from 'examples/LayoutContainers/DashboardLayout';
import DashboardNavbar from 'examples/Navbars/DashboardNavbar';
import Footer from 'examples/Footer';
import ProfileInfoCard from 'examples/Cards/InfoCards/ProfileInfoCard';
import Header from 'layouts/worker/profile/components/Header';
import { useEffect, useState } from 'react';

function Overview() {
  const [profile, setProfile] = useState(null);
  const [appliedJobs, setAppliedJobs] = useState([]);
  const workerId = 1; // 실제로는 로그인 정보에서 받아야 함

  useEffect(() => {
    fetch(`/api/worker/${workerId}`)
      .then((res) => res.json())
      .then((data) => {
        setProfile({
          성명: data.name,
          전화번호: data.phoneNumber,
          평점: data.rating,
        });
        setAppliedJobs(data.appliedJobs || []);
      });
  }, [workerId]);

  return (
    <DashboardLayout>
      <DashboardNavbar />
      <MDBox mb={2} />
      <Header>
        <MDBox mt={5} mb={3}>
          <Grid container spacing={1}>
            <Grid item xs={12} md={6} xl={4} sx={{ display: 'flex' }}>
              <Divider orientation="vertical" sx={{ ml: -2, mr: 1 }} />
              {profile && (
                <ProfileInfoCard
                  title="프로필 정보"
                  info={profile}
                  action={{ route: '', tooltip: 'Edit Profile' }}
                  shadow={false}
                />
              )}
            </Grid>
          </Grid>
        </MDBox>
        {/* 내가 지원한 근무 리스트 */}
        <MDBox mt={4}>
          <MDTypography variant="h6" fontWeight="medium" mb={2}>
            내가 지원한 근무
          </MDTypography>
          {appliedJobs.length === 0 ? (
            <MDTypography variant="body2">아직 지원한 근무가 없습니다.</MDTypography>
          ) : (
            <ul style={{ listStyleType: 'none', paddingLeft: 0 }}>
              {appliedJobs.map((job, idx) => (
                <li key={job.applicationId} style={{ marginBottom: 16 }}>
                  <MDTypography variant="body1" fontWeight="bold">
                    {idx + 1}. {job.jobDescription} ({job.workLocation})
                  </MDTypography>
                  <MDTypography variant="body2" color="text">
                    지원일: {job.appliedAt ? new Date(job.appliedAt).toLocaleString() : '-'}
                    <br />
                    사장님: {job.ownerName} / {job.ownerPhone}
                    <br />
                    상태: {job.status}
                  </MDTypography>
                </li>
              ))}
            </ul>
          )}
        </MDBox>
      </Header>
      <Footer />
    </DashboardLayout>
  );
}

export default Overview;
