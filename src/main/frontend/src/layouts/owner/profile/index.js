import React, { useEffect, useState } from 'react';
import Grid from '@mui/material/Grid';
import Divider from '@mui/material/Divider';
import Dialog from '@mui/material/Dialog';
import DialogTitle from '@mui/material/DialogTitle';
import DialogContent from '@mui/material/DialogContent';
import DialogActions from '@mui/material/DialogActions';
import Button from '@mui/material/Button';
import MDBox from 'components/MDBox';
import MDTypography from 'components/MDTypography';
import DashboardLayout from 'examples/LayoutContainers/DashboardLayout';
import DashboardNavbar from 'examples/Navbars/DashboardNavbar';
import Footer from 'examples/Footer';
import ProfileInfoCard from 'examples/Cards/InfoCards/ProfileInfoCard';
import Header from 'layouts/owner/profile/components/Header';

function Overview() {
  const [profile, setProfile] = useState(null);
  const [jobPostings, setJobPostings] = useState([]);
  const [dialogOpen, setDialogOpen] = useState(false);
  const [selectedWorker, setSelectedWorker] = useState(null);
  const [acceptDialogOpen, setAcceptDialogOpen] = useState(false);
  const [acceptTarget, setAcceptTarget] = useState(null);
  const ownerId = 1;

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

  // 버튼 스타일 통일
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
    marginLeft: 16,
    border: 'none',
    cursor: 'pointer',
    boxSizing: 'border-box',
    verticalAlign: 'middle',
    lineHeight: '30px',
    padding: 0,
  };

  // 근무 완료하기 버튼 클릭
  const handleWorkComplete = (worker) => {
    setSelectedWorker(worker);
    setDialogOpen(true);
  };

  // 평가 페이지로 이동 + 서버에 '마감' 상태 저장
  const goToEvaluation = async () => {
    setDialogOpen(false);
    try {
      await fetch(
        `/api/applications/${selectedWorker.applicationId}/complete`,
        { method: 'POST' }
      );
      const res = await fetch(`/api/owner/${ownerId}`);
      const data = await res.json();
      setJobPostings(data.jobPostings || []);
      window.location.href = `/pages/WorkerEvaluation?jobPostingId=${selectedWorker.jobPostingId}`;
    } catch (e) {
      alert('마감 처리에 실패했습니다.');
    }
  };

  // 지원 수락/거절 함수
  const acceptApplication = (applicationId) => {
    setAcceptTarget(applicationId);
    setAcceptDialogOpen(true);
  };

  const handleAccept = async () => {
    setAcceptDialogOpen(false);
    await fetch(`/api/owner/applications/${acceptTarget}/accept`, { method: 'POST' });
    window.location.reload();
  };

  const handleReject = async () => {
    setAcceptDialogOpen(false);
    await fetch(`/api/owner/applications/${acceptTarget}/reject`, { method: 'POST' });
    window.location.reload();
  };

  // 버튼 상태 및 클릭 핸들러 결정 함수
  const getButtonProps = (worker, posting) => {
    if (worker.status === '마감') {
      return {
        label: '마감',
        color: '#bdbdbd',
        onClick: null,
        disabled: true,
      };
    }
    if (worker.status === '대기') {
      return {
        label: '수락',
        color: '#1976d2',
        onClick: () => acceptApplication(worker.applicationId),
        disabled: false,
      };
    }
    if (worker.status === '승인') {
      return {
        label: '근무 완료',
        color: '#ff9800',
        onClick: () =>
          handleWorkComplete({
            ...worker,
            jobPostingId: posting.jobPostingId,
          }),
        disabled: false,
      };
    }
    if (worker.status === '거절') {
      return {
        label: '거절됨',
        color: '#e53935',
        onClick: null,
        disabled: true,
      };
    }
    // 기타 예외
    return {
      label: worker.status,
      color: '#bdbdbd',
      onClick: null,
      disabled: true,
    };
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
                <ul style={{ marginTop: 8, listStyleType: 'none', paddingLeft: 0 }}>
                  {posting.applicants.length === 0 ? (
                    <li>아직 지원자가 없습니다.</li>
                  ) : (
                    posting.applicants.map((worker, idx) => {
                      const btnProps = getButtonProps(worker, posting);
                      return (
                        <li
                          key={worker.applicationId}
                          style={{
                            marginBottom: 8,
                            display: 'flex',
                            alignItems: 'center',
                          }}
                        >
                          <span style={{ minWidth: 350, display: 'flex', alignItems: 'center' }}>
                            {idx + 1}. 이름: {worker.name} / 연락처: {worker.phoneNumber}
                            <span style={{ marginLeft: 16, fontWeight: 500, color: '#1976d2' }}>
                              평점: {typeof worker.rating === 'number' ? worker.rating.toFixed(1) : '0.0'}
                            </span>
                          </span>
                          <button
                            onClick={btnProps.onClick}
                            disabled={btnProps.disabled}
                            style={{
                              ...baseButtonStyle,
                              background: btnProps.color,
                              cursor: btnProps.disabled ? 'default' : 'pointer',
                            }}
                          >
                            {btnProps.label}
                          </button>
                        </li>
                      );
                    })
                  )}
                </ul>
              </MDBox>
            ))
          )}
        </MDBox>
        {/* 근무 완료 → 평가 팝업 */}
        <Dialog open={dialogOpen} onClose={() => setDialogOpen(false)}>
          <DialogTitle>평가 바로가기</DialogTitle>
          <DialogContent>
            <MDTypography variant="body1">
              근무가 완료되었습니다.<br />
              해당 알바생을 평가하시겠습니까?
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
        {/* 수락/거절 팝업 */}
        <Dialog open={acceptDialogOpen} onClose={() => setAcceptDialogOpen(false)}>
          <DialogTitle>지원자 승인/거절</DialogTitle>
          <DialogContent>
            <MDTypography variant="body1">
              이 지원자를 승인 또는 거절하시겠습니까?
            </MDTypography>
          </DialogContent>
          <DialogActions>
            <Button onClick={handleReject} color="error">
              거절
            </Button>
            <Button onClick={handleAccept} color="primary" autoFocus>
              승인
            </Button>
          </DialogActions>
        </Dialog>
      </Header>
      <Footer />
    </DashboardLayout>
  );
}

export default Overview;
