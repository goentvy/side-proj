import axios from 'axios';

const api = axios.create({
  baseURL: '/api/onbid',
  headers: {
    'Content-Type': 'application/json',
  },
});

export default api;