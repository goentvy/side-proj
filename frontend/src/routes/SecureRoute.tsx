import { Navigate } from 'react-router-dom';
import { Loader2 } from 'lucide-react';
import { cn } from '@/lib/utils';
import { useAuthStore } from '@/store/authStore';

interface Props {
  allowedRoles?: string[];
  children: React.ReactNode;
}

export default function SecureRoute({ allowedRoles, children }: Props) {
  const { user, isLoading } = useAuthStore();

  if (isLoading) {
    return (
      <div className="flex items-center justify-center min-h-screen">
        <Loader2 className={cn('animate-spin text-gray-500 w-8 h-8')} />
      </div>
    );
  }

  if (!user) {
    return <Navigate to="/unauthorized" replace />;
  }

  if (allowedRoles && !allowedRoles.includes(user.role)) {
    return <Navigate to="/unauthorized" replace />;
  }

  return <>{children}</>;
}