import { useEffect, useState } from 'react';
import { Navigate } from 'react-router-dom';
import axios from 'axios';
import { Loader2 } from 'lucide-react';
import { cn } from '@/lib/utils';

interface Props {
  allowedRoles?: string[]; // 없으면 인증만 체크
  children: (user: { email: string; role: string }) => React.ReactNode;
}

export default function SecureRoute({ allowedRoles, children }: Props) {
  const [user, setUser] = useState<{ email: string; role: string } | null>(null);
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
        const email = res.data.email;
        const isAllowed = !allowedRoles || allowedRoles.includes(role);
        setAuthorized(isAllowed);
        if(isAllowed) setUser({ email, role });
      })
      .catch(() => {
        localStorage.removeItem('token');
        setAuthorized(false);
      });
  }, []);

  if (authorized === null) {
    return (
      <div className="flex items-center justify-center min-h-screen">
        <Loader2 className={cn('animate-spin text-gray-500 w-8 h-8')} />
      </div>
    );
  }

  if (!authorized || !user) return <Navigate to="/unauthorized" replace />;
  return <>{children(user)}</>;
}