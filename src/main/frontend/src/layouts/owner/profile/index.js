// @mui material components
import Grid from '@mui/material/Grid';
import Divider from '@mui/material/Divider';

// Material Dashboard 2 React components
import MDBox from 'components/MDBox';
import MDTypography from 'components/MDTypography';

// Material Dashboard 2 React example components
import DashboardLayout from 'examples/LayoutContainers/DashboardLayout';
import DashboardNavbar from 'examples/Navbars/DashboardNavbar';
import Footer from 'examples/Footer';
import ProfileInfoCard from 'examples/Cards/InfoCards/ProfileInfoCard';

// Overview page components
import Header from 'layouts/owner/profile/components/Header';
import { useEffect, useState } from 'react';

function Overview() {
  const [profile, setProfile] = useState(null);

  useEffect(() => {
    fetch('/api/owner/1')
      .then((res) => res.json())
      .then((data) => {
        setProfile({
          성명: data.name,
          전화번호: data.phoneNumber,
          평점: data.rating,
        });
      });
  }, []);

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
        <MDBox pt={2} px={2} lineHeight={1.25}>
          <MDTypography variant="h6" fontWeight="medium">
            Projects
          </MDTypography>
          <MDBox mb={1}>
            <MDTypography variant="button" color="text">
              Architects design houses
            </MDTypography>
          </MDBox>
        </MDBox>
        {/* 밑에는 지원한 작업들 데이터 불러오는 파일 */}
      </Header>
      <Footer />
    </DashboardLayout>
  );
}

export default Overview;
