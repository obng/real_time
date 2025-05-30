import React, { useState, useEffect } from 'react';
import { Box, Typography, Button } from '@mui/material';
import ReactStars from 'react-rating-stars-component';

export default function OwnerEvaluationPage() {
  const ownerId = 1;
  const [form, setForm] = useState({
    paymentPunctuality: 0,
    workEnvironment: 0,
    workingHours: 0,
  });
  const [summary, setSummary] = useState(null);

  useEffect(() => {
    fetch(`/api/owner/summary/${ownerId}`)
      .then((res) => res.json())
      .then(setSummary)
      .catch((err) => {
        console.error('점수 요약 불러오기 실패:', err);
      });
  }, [ownerId]);

  const handleStar = (name, value) => {
    setForm({ ...form, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    await fetch(`/api/evaluation/worker-to-owner/${ownerId}`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(form),
    });
    alert('평가가 등록되었습니다!');
    window.location.href = 'http://localhost:3000/worker/profile';
    };
   // fetch(`/api/evaluation/summary/${ownerId}`)
     // .then((res) => res.json())
      //.then(setSummary);
    //};


  return (
    <Box
      minHeight="100vh"
      display="flex"
      flexDirection="column"
      justifyContent="center"
      alignItems="center"
      bgcolor="#f9f9f9"
    >
      <Box
        p={4}
        bgcolor="white"
        borderRadius={3}
        boxShadow={3}
        minWidth={350}
        maxWidth={400}
        width="100%"
        display="flex"
        flexDirection="column"
        alignItems="center"
      >
        <Typography variant="h4" gutterBottom align="center">
          사장님은 어떠셨나요?
        </Typography>
        <form onSubmit={handleSubmit} style={{ width: '100%' }}>
          <Box mb={2}>
            <Typography align="center">급여 지급의 정확성</Typography>
            <Box display="flex" justifyContent="center">
            <ReactStars
              count={5}
              size={30}
              isHalf={true}
              value={form.paymentPunctuality}
              onChange={(v) => handleStar('paymentPunctuality', v)}
            />
            </Box>
          </Box>
          <Box mb={2}>
            <Typography align="center">근무 환경</Typography>
            <Box display="flex" justifyContent="center">
            <ReactStars
              count={5}
              size={30}
              isHalf={true}
              value={form.paymentPunctuality}
              onChange={(v) => handleStar('paymentPunctuality', v)}
            />
            </Box>
          </Box>
          <Box mb={2}>
            <Typography align="center">근무 시간 준수</Typography>
            <Box display="flex" justifyContent="center">
            <ReactStars
              count={5}
              size={30}
              isHalf={true}
              value={form.paymentPunctuality}
              onChange={(v) => handleStar('paymentPunctuality', v)}
            />
            </Box>
          </Box>
          <Box display="flex" justifyContent="center">
            <Button
              type="submit"
              variant="contained"
              color="primary"
              sx={{ color: 'white' }}
            >
              평가 등록
            </Button>
          </Box>
        </form>
        <Box mt={4} width="100%">
          <Typography variant="h6" align="center">
            평가 요약
          </Typography>
          {summary ? (
            <ul style={{ textAlign: 'center', padding: 0, listStyle: 'none' }}>
              <li>이름: {summary.ownerName}</li>
              <li>급여 지급의 정확성: {summary.paymentPunctualityScore}</li>
              <li>근무 환경: {summary.workEnvironmentScore}</li>
              <li>근무 시간 준수: {summary.workingHoursScore}</li>
            </ul>
          ) : (
            <Typography align="center">불러오는 중...</Typography>
          )}
        </Box>
      </Box>
    </Box>
  );
}