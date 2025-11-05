// src/routes/RoleRoute.tsx
import { useEffect, useState } from 'react';
import { Navigate } from 'react-router-dom';
import axios from 'axios';

interface Props {
  allowedRoles: string[];
  children: React.ReactNode;
}

export default function RoleRoute({ allowedRoles, children }: Props) {
  const [authorized, setAuthorized] = useState<boolean | null>(null);

  useEffect(() => {
    const token = localStorage.getItem('token');
    if (!token) {
      setAuthorized(false);
      return;
    }

    axios
      .get('/api/me', {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((res) => {
        const role = res.data.role.trim().toUpperCase();
        setAuthorized(allowedRoles.includes(role));
      })
      .catch(() => {
        localStorage.removeItem('token');
        setAuthorized(false);
      });
  }, []);

  if (authorized === null) return null;
  if (!authorized) return <Navigate to="/unauthorized" replace />;
  return <>{children}</>;
}