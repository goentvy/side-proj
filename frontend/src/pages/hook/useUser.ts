import { useEffect, useState } from 'react';
import axios from 'axios';

export function useUser() {
  const [user, setUser] = useState<{ email: string; role: string } | null>(null);

  useEffect(() => {
    const token = localStorage.getItem('token');
    if (!token) return;

    axios
      .get('/api/me', {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then(res => setUser(res.data))
      .catch(() => setUser(null));
  }, []);

  return user;
}