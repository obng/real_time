import Grid from '@mui/material/Grid';
import Divider from '@mui/material/Divider';
import MDBox from 'components/MDBox';
import MDTypography from 'components/MDTypography';
import DashboardLayout from 'examples/LayoutContainers/DashboardLayout';
import DashboardNavbar from 'examples/Navbars/DashboardNavbar';
import Footer from 'examples/Footer';
import ProfileInfoCard from 'examples/Cards/InfoCards/ProfileInfoCard';
import Header from 'layouts/owner/profile/components/Header';
import { useEffect, useState } from 'react';

function Overview() {
  const [profile, setProfile] = useState(null);
  const [jobPostings, setJobPostings] = useState([]);
  const ownerId = 1; // 사장님 id (로그인 정보에서 받아올 수도 있음)

  useEffect(() => {
    fetch(`/api/owner/${ownerId}`)
      .then((res) => res.json())
      .then((data) => {
        setProfile({
          성명: data.name,
          전화번호: data.phoneNumber,
          평점: data.rating,
        });
        setJobPostings(data.jobPostings || []);
      });
  }, [ownerId]);

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
        {/* 지원자 목록 표시 */}
        <MDBox mt={4}>
          <MDTypography variant="h6" fontWeight="medium" mb={2}>
            내가 올린 공고의 지원자 목록
          </MDTypography>
          {jobPostings.length === 0 ? (
            <MDTypography variant="body2">아직 지원자가 없습니다.</MDTypography>
          ) : (
            jobPostings.map((posting) => (
              <MDBox key={posting.jobPostingId} mb={3} p={2} border="1px solid #eee" borderRadius="8px">
                <MDTypography variant="subtitle1" fontWeight="bold">
                  [{posting.jobPostingId}] {posting.jobDescription} ({posting.workLocation})
                </MDTypography>
                <ul style={{ marginTop: 8 }}>
                  {posting.applicants.length === 0 ? (
                    <li>아직 지원자가 없습니다.</li>
                  ) : (
                    posting.applicants.map((worker) => (
                      <li key={worker.id}>
                        이름: {worker.name} / 연락처: {worker.phoneNumber}
                      </li>
                    ))
                  )}
                </ul>
              </MDBox>
            ))
          )}
        </MDBox>
      </Header>
      <Footer />
    </DashboardLayout>
  );
}

export default Overview;
