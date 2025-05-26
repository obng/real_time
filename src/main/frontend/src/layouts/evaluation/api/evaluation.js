import axios from 'axios';

const API_BASE = 'http://localhost:8080/api/evaluation';

export const submitOwnerToWorker = async (workerId, rating) => {
  return axios.post(`${API_BASE}/owner-to-worker`, { workerId, rating });
};

export const submitWorkerToOwner = async (ownerId, rating) => {
  return axios.post(`${API_BASE}/worker-to-owner`, { ownerId, rating });
};

