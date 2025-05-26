import React, { forwardRef, useState } from 'react';
import PropTypes from 'prop-types';
import { submitOwnerToWorker } from '../../api/ownerEvaluateapi.js';
import EvaluationForm from './EvaluationForm';
import { Button, Typography } from '@mui/material';
import Rating from '@mui/material/Rating';

const OwnerEvaluate = forwardRef(({ workerId, onSubmit }, ref) => {
  const [PaymentPunctuality, setPaymentPunctuality] = useState(0);
  const [LateDelta, setLateDelta] = useState(0);
  const [AbsentDelta, setAbsentDelta] = useState(0);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await submitOwnerToWorker(workerId, {
        sincerityDelta: PaymentPunctuality,
        lateDelta: LateDelta,
        absentDelta: AbsentDelta,
      });
      alert('평가가 성공적으로 제출되었습니다.');
      if (onSubmit)
        onSubmit({
          sincerityDelta: PaymentPunctuality,
          lateDelta: LateDelta,
          absentDelta: AbsentDelta,
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
          알바들을 평가해주세요
        </Typography>
        <Typography gutterBottom>알바생은 성실합니까?</Typography>
        <Rating
          name="PaymentPunctuality"
          value={PaymentPunctuality}
          onChange={(e, newValue) => setPaymentPunctuality(newValue)}
          max={5}
        />

        <Typography gutterBottom>알바생이 지각을 하지 않습니까?</Typography>
        <Rating
          name="LateDelta"
          value={LateDelta}
          onChange={(e, newValue) => setLateDelta(newValue)}
          max={5}
        />

        <Typography gutterBottom>알바생이 도망을 가지 않습니까? </Typography>
        <Rating
          name="AbsentDelta"
          value={AbsentDelta}
          onChange={(e, newValue) => setAbsentDelta(newValue)}
          max={5}
        />

        <Button type="submit" variant="contained" sx={{ mt: 2 }}>
          제출
        </Button>
      </form>
    </EvaluationForm>
  );
});

OwnerEvaluate.propTypes = {
  workerId: PropTypes.number.isRequired,
  onSubmit: PropTypes.func,
};

OwnerEvaluate.defaultProps = {
  onSubmit: null,
};

export default OwnerEvaluate;
