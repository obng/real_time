import React, { forwardRef, useState } from 'react';
import PropTypes from 'prop-types';
import { submitWorkerToOwner } from '../../api/workerEvaluateapi.js';
import EvaluationForm from './EvaluationForm';
import { Button, Typography } from '@mui/material';
import Rating from '@mui/material/Rating';

const WorkerEvaluate = forwardRef(({ ownerId, onSubmit }, ref) => {
  const [PaymentPunctuality, setPaymentPunctuality] = useState(0);
  const [WorkEnvironment, setWorkEnvironment] = useState(0);
  const [WorkingHours, setWorkingHours] = useState(0);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await submitWorkerToOwner(ownerId, {
        paymentPunctuality: PaymentPunctuality,
        workEnvironment: WorkEnvironment,
        workingHours: WorkingHours,
      });
      alert('평가가 성공적으로 제출되었습니다.');
      if (onSubmit)
        onSubmit({
          paymentPunctuality: PaymentPunctuality,
          workEnvironment: WorkEnvironment,
          workingHours: WorkingHours,
        });
    } catch (error) {
      console.error('제출 실패:', error);
      alert('평가 제출에 실패했습니다.');
    }
  };

  return (
    <EvaluationForm ref={ref}>
      <form onSubmit={handleSubmit}>
        <Typography variant="h6" gutterBottom>
          사장님들을 평가해주세요
        </Typography>
        <Typography gutterBottom>사장님이 급여 재떄 지급합니까?</Typography>
        <Rating
          name="PaymentPunctuality"
          value={PaymentPunctuality}
          onChange={(e, newValue) => setPaymentPunctuality(newValue)}
          max={5}
        />

        <Typography gutterBottom>근무 환경은 만족스럽습니까?</Typography>
        <Rating
          name="WorkEnvironment"
          value={WorkEnvironment}
          onChange={(e, newValue) => setWorkEnvironment(newValue)}
          max={5}
        />

        <Typography gutterBottom>퇴근 시간을 잘 지켜주십니까?</Typography>
        <Rating
          name="WorkingHours"
          value={WorkingHours}
          onChange={(e, newValue) => setWorkingHours(newValue)}
          max={5}
        />
        <Button type="submit" variant="contained" sx={{ mt: 2 }}>
          제출
        </Button>
      </form>
    </EvaluationForm>
  );
});

WorkerEvaluate.propTypes = {
  ownerId: PropTypes.number.isRequired,
  onSubmit: PropTypes.func,
};

WorkerEvaluate.defaultProps = {
  onSubmit: null,
};

export default WorkerEvaluate;
