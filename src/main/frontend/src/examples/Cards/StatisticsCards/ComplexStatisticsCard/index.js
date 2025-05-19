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
  onApply,
}) {
  const [open, setOpen] = useState(false);

  // '신청하기' 버튼 클릭 시 모달 오픈
  const handleOpen = () => setOpen(true);
  // 모달 닫기
  const handleClose = () => setOpen(false);

  // 모달에서 '확인' 클릭 시
  const handleConfirm = () => {
    setOpen(false);
    if (onApply) onApply();
    window.alert('신청이 완료되었습니다!');
  };

  return (
    <Card>
      {/* 상부 */}
      <MDBox display="flex" justifyContent="space-between" pt={1} px={2}>
        <MDBox textAlign="left" lineHeight={1.25} width="100%">
          <MDTypography variant="button" fontWeight="light" color="text">
            {workLocation}
          </MDTypography>
          <MDTypography variant="h5">{jobDescription}</MDTypography>
          <MDTypography variant="h5">
            {typeof dailyWage === 'number' ? `${dailyWage.toLocaleString()}원` : dailyWage}
          </MDTypography>
        </MDBox>
      </MDBox>
      <Divider />
      {/* 하부 */}
      <MDBox pb={2} px={2}>
        <MDTypography component="p" variant="button" display="flex" fontWeight="bold">
          선발 인원 수 : {numberOfWorkers}
        </MDTypography>
        <MDBox display="flex" justifyContent="flex-end" mt={2}>
          <MDButton color="info" onClick={handleOpen}>
            신청하기
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
          <Button onClick={handleClose} color="secondary">
            취소
          </Button>
          <Button onClick={handleConfirm} color="primary" autoFocus>
            확인
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
  onApply: PropTypes.func,
};

export default ComplexStatisticsCard;
