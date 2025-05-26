import PropTypes from 'prop-types';

// @mui material components
import Card from '@mui/material/Card';
import Divider from '@mui/material/Divider';
import Dialog from '@mui/material/Dialog';
import DialogTitle from '@mui/material/DialogTitle';
import DialogContent from '@mui/material/DialogContent';
import DialogActions from '@mui/material/DialogActions';
import Button from '@mui/material/Button';

// Material Dashboard 2 React components
import MDBox from 'components/MDBox';
import MDTypography from 'components/MDTypography';
import MDButton from 'components/MDButton';

import { useState } from 'react';

function ComplexStatisticsCard({
  workLocation,
  jobDescription,
  dailyWage,
  numberOfWorkers,
  createdAt,
  onApply,
}) {
  const [open, setOpen] = useState(false);
  const [applied, setApplied] = useState(false); // 지원 완료 상태
  const [loading, setLoading] = useState(false); // 지원 중 로딩

  // '신청하기' 버튼 클릭 시 모달 오픈
  const handleOpen = () => setOpen(true);
  // 모달 닫기
  const handleClose = () => setOpen(false);

  // 모달에서 '확인' 클릭 시
  const handleConfirm = async () => {
    setLoading(true);
    try {
      if (onApply) {
        await onApply(); // 비동기 지원 처리
      }
      setApplied(true);
      window.alert('신청이 완료되었습니다!');
    } catch (e) {
      window.alert('신청에 실패했습니다.');
    }
    setLoading(false);
    setOpen(false);
  };

  return (
    <Card>
      {/* 상부 */}
      <MDBox display="flex" justifyContent="space-between" pt={1} px={2}>
        <MDBox textAlign="left" lineHeight={1.25} width="100%">
          <MDTypography variant="button" fontWeight="light" color="text" >
            {workLocation}
          </MDTypography>
          <MDTypography variant="h5">{jobDescription}</MDTypography>
          <MDTypography variant="h5">
            {typeof dailyWage === 'number'
              ? `${dailyWage.toLocaleString()}원`
              : dailyWage}
          </MDTypography>
        </MDBox>
      </MDBox>
      <Divider />
      {/* 하부 */}
      <MDBox pb={2} px={2}>
        <MDTypography component="p" variant="button" display="flex" fontWeight="bold">
          선발 인원 수 : {numberOfWorkers}
        </MDTypography>
        {/* 작성일시 표시 */}
        {createdAt && (
          <MDTypography component="p" variant="caption" color="text" mt={0.5}>
            작성일시: {new Date(createdAt).toLocaleString()}
          </MDTypography>
        )}
        <MDBox display="flex" justifyContent="flex-end" mt={2}>
          <MDButton
            color={applied ? 'success' : 'info'}
            onClick={handleOpen}
            disabled={applied || loading}
          >
            {applied ? '신청 완료' : loading ? '신청 중...' : '신청하기'}
          </MDButton>
        </MDBox>
      </MDBox>

      {/* 확인 모달 */}
      <Dialog open={open} onClose={handleClose}>
        <DialogTitle>지원 확인</DialogTitle>
        <DialogContent>
          <MDTypography variant="body1">해당 근무에 지원하시겠습니까?</MDTypography>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose} color="secondary" disabled={loading}>
            취소
          </Button>
          <Button onClick={handleConfirm} color="primary" autoFocus disabled={loading}>
            {loading ? '신청 중...' : '확인'}
          </Button>
        </DialogActions>
      </Dialog>
    </Card>
  );
}

ComplexStatisticsCard.defaultProps = {
  color: 'info',
  dailyWage: 'Not specified',
  onApply: null,
  createdAt: null,
};

ComplexStatisticsCard.propTypes = {
  color: PropTypes.oneOf([
    'primary',
    'secondary',
    'info',
    'success',
    'warning',
    'error',
    'light',
    'dark',
  ]),
  workLocation: PropTypes.string.isRequired,
  jobDescription: PropTypes.oneOfType([PropTypes.string, PropTypes.number]).isRequired,
  dailyWage: PropTypes.oneOfType([PropTypes.string, PropTypes.number]).isRequired,
  numberOfWorkers: PropTypes.oneOfType([PropTypes.string, PropTypes.number]).isRequired,
  createdAt: PropTypes.oneOfType([PropTypes.string, PropTypes.instanceOf(Date)]),
  onApply: PropTypes.func,
};

export default ComplexStatisticsCard;
