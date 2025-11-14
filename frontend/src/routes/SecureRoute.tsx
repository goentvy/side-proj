import { Navigate } from 'react-router-dom';
import { Loader2 } from 'lucide-react';
import { useAuthStore } from '@/store/authStore';

interface SecureRouteProps {
  children: React.ReactNode;
  allowedRoles?: string[];
  fallback?: React.ReactNode;
  redirectTo?: string;
  unauthorizedMessage?: string;
}

export default function SecureRoute({
  children,
  allowedRoles,
  fallback,
  redirectTo = '/login',
  unauthorizedMessage,
}: SecureRouteProps) {
  const { user, isLoading } = useAuthStore();

  if (isLoading) {
    return (
      fallback ?? (
        <div className="flex items-center justify-center min-h-screen">
          <Loader2 className="animate-spin text-gray-500 w-8 h-8" />
        </div>
      )
    );
  }

  if (!user) {
    return <Navigate to={redirectTo} replace />;
  }

  if (allowedRoles && !allowedRoles.includes(user.role)) {
    return (
      <>
        {unauthorizedMessage && (
          <div className="text-center mt-10 text-red-500 text-sm">
            {unauthorizedMessage}
          </div>
        )}
        <Navigate to="/unauthorized" replace />
      </>
    );
  }

  return <>{children}</>;
}