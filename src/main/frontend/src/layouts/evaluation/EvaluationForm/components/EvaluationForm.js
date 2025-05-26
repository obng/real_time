import { styled } from '@mui/material/styles';
import Box from '@mui/material/Box';

const EvaluationForm = styled(Box)(({ theme }) => ({
  padding: theme.spacing(2),
  borderRadius: theme.shape.borderRadius,
  backgroundColor: theme.palette.background.paper,
  boxShadow: theme.shadows[1],
  maxWidth: 400,
  margin: 'auto',
}));

export default EvaluationForm;
