import React, { useState, useEffect } from 'react';
import { Box, Typography, Button } from '@mui/material';
import ReactStars from 'react-rating-stars-component';

export default function WorkerEvaluationPage() {
  const workerId = 1; // 실제로는 useParams 등으로 받아야 함
  const [form, setForm] = useState({
    sincerityDelta: 0,
    lateDelta: 0,
    absentDelta: 0,
  });
  const [summary, setSummary] = useState(null);

  useEffect(() => {
    fetch(`/api/evaluate/summary/${workerId}`)
      .then((res) => res.json())
      .then(setSummary);
  }, [workerId]);

  const handleStar = (name, value) => {
    setForm({ ...form, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    await fetch(`/api/evaluate/owner-to-worker/${workerId}`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(form),
    });
    alert('평가가 등록되었습니다!');
    window.location.href = 'http://localhost:3000/owner/profile'; // ← 이 부분 추가
  };

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
          알바생은 어땠나요?
        </Typography>
        <form onSubmit={handleSubmit} style={{ width: '100%' }}>
          <Box mb={2}>
            <Typography align="center">성실도</Typography>
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
            <Typography align="center">지각</Typography>
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
            <Typography align="center">결근</Typography>
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
            <Button type="submit" variant="contained" color="primary">
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
              <li>이름: {summary.workerName}</li>
              <li>성실도: {summary.sincerityScore}</li>
              <li>지각: {summary.lateScore}</li>
              <li>결근: {summary.absentScore}</li>
            </ul>
          ) : (
            <Typography align="center">불러오는 중...</Typography>
          )}
        </Box>
      </Box>
    </Box>
  );
}
