import { useEffect, useState } from 'react';
import { Navigate } from 'react-router-dom';
import axios from 'axios';

interface Props {
  children: React.ReactNode;
}

export default function PrivateRoute({ children}: Props) {
    const [authorized, setAuthorized] = useState<boolean | null>(null);

    useEffect(() => {
        const token = localStorage.getItem('token');
        if(!token) {
            setAuthorized(false);
            return;
        }

        axios.get('/api/me', {
            headers: { Authorization: `Bearer ${token}` },
        })
        .then(() => setAuthorized(true))
        .catch(() => {
            localStorage.removeItem('token');
            setAuthorized(false);
        });
    }, []);

    if(authorized === null) return null; // 로딩 중
    if(!authorized) return <Navigate to="/login" replace />;
    return <>{children}</>;
}