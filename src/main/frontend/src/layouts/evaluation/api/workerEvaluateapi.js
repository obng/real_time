import axios from 'axios';

export async function submitWorkerToOwner(ownerId, rating) {
  try {
    const response = await axios.post('/api/evaluate/worker-to-owner', {
      ownerId,
      ...rating,
    });
    return response.data;
  } catch (error) {
    throw error;
  }
}
