import React from 'react';
import { OwnerEvaluate, WorkerEvaluate } from './EvaluationForm/components';
import Container from '@mui/material/Container';
import Typography from '@mui/material/Typography';
import { useRole } from 'RoleContext';

function EvaluationPage({ userId }) {
  const { role: userRole } = useRole(); // 역할 가져오기

  const handleOwnerSubmit = async ({ PaymentPunctuality, LateDelta, AbsentDelta }) => {
    console.log('사장이 알바를 평가했습니다:', {
      PaymentPunctuality,
      LateDelta,
      AbsentDelta,
    });

    alert(`사장이 평가한 점수:
    • 성실도: ${PaymentPunctuality}
    • 지각: ${LateDelta}
    • 무단결근: ${AbsentDelta}`);

    await submitOwnerToWorker({ workerId: userId, PaymentPunctuality, LateDelta, AbsentDelta });
  };

  const handleWorkerSubmit = async ({ PaymentPunctuality, WorkEnvironment, WorkingHours }) => {
    console.log('알바가 사장을 평가했습니다:', {
      PaymentPunctuality,
      WorkEnvironment,
      WorkingHours,
    });

    alert(`알바가 평가한 점수:
    • 급여 성실도: ${PaymentPunctuality}
    • 근무 환경: ${WorkEnvironment}
    • 근무 시간: ${WorkingHours}`);

    await submitWorkerToOwner({
      ownerId: userId,
      PaymentPunctuality,
      WorkEnvironment,
      WorkingHours,
    });
  };

  return (
    <Container sx={{ mt: 4 }}>
      <Typography variant="h4" gutterBottom textAlign="center">
        !! 평가를 해주세요 !!
      </Typography>
      <Typography variant="subtitle1" textAlign="center" color="textSecondary" sx={{ mb: 2 }}>
        현재 역할: {userRole}
      </Typography>

      {userRole === 'owner' && <OwnerEvaluate workerId={userId} onSubmit={handleOwnerSubmit} />}

      {userRole === 'worker' && <WorkerEvaluate ownerId={userId} onSubmit={handleWorkerSubmit} />}

      {!['owner', 'worker'].includes(userRole) && (
        <Typography textAlign="center" color="error">
          유효하지 않은 사용자 역할입니다.
        </Typography>
      )}
    </Container>
  );
}

export default EvaluationPage;
