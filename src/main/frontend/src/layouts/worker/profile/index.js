import Grid from '@mui/material/Grid';
import Divider from '@mui/material/Divider';
import MDBox from 'components/MDBox';
import MDTypography from 'components/MDTypography';
import DashboardLayout from 'examples/LayoutContainers/DashboardLayout';
import DashboardNavbar from 'examples/Navbars/DashboardNavbar';
import Footer from 'examples/Footer';
import ProfileInfoCard from 'examples/Cards/InfoCards/ProfileInfoCard';
import Header from 'layouts/worker/profile/components/Header';
import Dialog from '@mui/material/Dialog';
import DialogTitle from '@mui/material/DialogTitle';
import DialogContent from '@mui/material/DialogContent';
import DialogActions from '@mui/material/DialogActions';
import Button from '@mui/material/Button';
import { useEffect, useState } from 'react';

function Overview() {
  const workerId = 1;
  const [profile, setProfile] = useState(null);
  const [appliedJobs, setAppliedJobs] = useState([]);
  const [completedJobs, setCompletedJobs] = useState([]); // 근무 완료된 applicationId 저장
  const [dialogOpen, setDialogOpen] = useState(false);
  const [selectedJob, setSelectedJob] = useState(null); // 평가할 근무

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
  }, []);

  // 버튼 스타일 (완전히 통일)
  const baseButtonStyle = {
    display: 'inline-block',
    color: 'white',
    borderRadius: 8,
    fontWeight: 'bold',
    fontSize: '0.7em',
    width: 90,
    height: 30,
    textAlign: 'center',
    marginTop: 8,
    marginRight: 8,
    border: 'none',
    cursor: 'pointer',
    boxSizing: 'border-box',
    verticalAlign: 'middle',
    lineHeight: '30px',
    padding: 0,
  };

  // 상태/근무완료 버튼 렌더링 함수
  const renderStatusAndWorkCompleteButton = (job) => {
    const isCompleted = completedJobs.includes(job.applicationId);

    // 근무 완료를 누른 경우: 마감 버튼만
    if (isCompleted) {
      return (
        <button
          style={{
            ...baseButtonStyle,
            background: '#757575',
            cursor: 'default',
          }}
          disabled
        >
          마감
        </button>
      );
    }

    // 승인 상태일 때만 근무 완료 버튼 보이게
    if (job.status === '승인') {
      return (
        <>
          <button
            style={{
              ...baseButtonStyle,
              background: '#1976d2',
              cursor: 'default',
              marginRight: 8,
            }}
            disabled
          >
            승인
          </button>
          <button
            style={{
              ...baseButtonStyle,
              background: '#ff9800',
            }}
            onClick={() => {
              setCompletedJobs((prev) => [...prev, job.applicationId]);
              setSelectedJob(job);
              setDialogOpen(true);
            }}
          >
            근무 완료
          </button>
        </>
      );
    }

    // 대기, 거절 등 다른 상태는 기존대로
    let statusColor = '#bdbdbd';
    let statusLabel = '대기';
    if (job.status === '거절') {
      statusColor = '#e53935';
      statusLabel = '거절';
    }
    return (
      <button
        style={{
          ...baseButtonStyle,
          background: statusColor,
          cursor: 'default',
        }}
        disabled
      >
        {statusLabel}
      </button>
    );
  };

  // 평가 페이지 이동 함수
  const goToEvaluation = () => {
    setDialogOpen(false);
    // 예시: 평가 페이지로 이동 (applicationId, jobPostingId, ownerId 등 넘길 수 있음)
    window.location.href = `/worker/evaluate/${selectedJob.applicationId}`;
  };

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
                <li
                  key={job.applicationId}
                  style={{
                    marginBottom: 20,
                  }}
                >
                  <MDTypography variant="body1" fontWeight="bold">
                    {idx + 1}. {job.jobDescription} ({job.workLocation})
                  </MDTypography>
                  <MDTypography variant="body2" color="text">
                    지원일: {job.appliedAt ? new Date(job.appliedAt).toLocaleString() : '-'}
                    <br />
                    사장님: {job.ownerName} / {job.ownerPhone}
                  </MDTypography>
                  {/* 상태/근무완료/마감 버튼 */}
                  <div>
                    {renderStatusAndWorkCompleteButton(job)}
                  </div>
                </li>
              ))}
            </ul>
          )}
        </MDBox>
        {/* 근무 완료 → 평가 팝업 */}
        <Dialog open={dialogOpen} onClose={() => setDialogOpen(false)}>
          <DialogTitle>평가 바로가기</DialogTitle>
          <DialogContent>
            <MDTypography variant="body1">
              근무가 완료되었습니다.<br />
              사장님을 평가하시겠습니까?
            </MDTypography>
          </DialogContent>
          <DialogActions>
            <Button onClick={() => setDialogOpen(false)} color="secondary">
              취소
            </Button>
            <Button onClick={goToEvaluation} color="primary" autoFocus>
              확인
            </Button>
          </DialogActions>
        </Dialog>
      </Header>
      <Footer />
    </DashboardLayout>
  );
}

export default Overview;
