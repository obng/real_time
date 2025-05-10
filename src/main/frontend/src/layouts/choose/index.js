import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import Button from '@mui/material/Button';
import Stack from '@mui/material/Stack';
import Typography from '@mui/material/Typography';

import { useMaterialUIController, setLayout } from 'context';
import { useRole } from 'RoleContext';

function Choose() {
  const navigate = useNavigate();
  const [, dispatch] = useMaterialUIController();
  const { setRole } = useRole();

  useEffect(() => {
    setLayout(dispatch, 'page');
  }, [dispatch]);

  const choose = (role) => {
    setRole(role);
    if (role === 'owner') {
      navigate('/owner/dashboard');
    } else if (role === 'worker') {
      navigate('/worker/dashboard');
    }
  };

  return (
    <div style={{ textAlign: 'center', marginTop: '200px' }}>
      <Typography variant="h4" gutterBottom>
        어느 쪽에 속하시나요?
      </Typography>
      <Stack direction="row" spacing={4} justifyContent="center">
        <Button
          variant="contained"
          color="primary"
          size="large"
          sx={{ color: '#fff' }}
          onClick={() => choose('owner')}
        >
          사장님
        </Button>
        <Button
          variant="contained"
          color="secondary"
          size="large"
          sx={{ color: '#fff' }}
          onClick={() => choose('worker')}
        >
          알바생
        </Button>
      </Stack>
    </div>
  );
}

export default Choose;
