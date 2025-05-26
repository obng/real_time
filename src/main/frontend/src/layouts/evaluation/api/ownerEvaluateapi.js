import axios from 'axios';

export async function submitOwnerToWorker(workerId, rating) {
  try {
    const response = await axios.post('/api/evaluate/owner-to-worker', {
      workerId,
      ...rating,
    });
    return response.data;
  } catch (error) {
    throw error;
  }
}

