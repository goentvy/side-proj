import axios from 'axios';

const apiAuction = axios.create({
  baseURL: '/api',
  headers: {
    'Content-Type': 'application/json',
  },
});

export default apiAuction;
