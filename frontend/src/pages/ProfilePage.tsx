import { useAuth } from '@/hook/useAuth';

export default function ProfilePage() {
  const { user, isLoading, isAuthenticated } = useAuth();

  if (isLoading) return <p>로딩 중...</p>;
  if (!isAuthenticated || !user) return <p>로그인이 필요합니다.</p>;

  return (
    <div>
      <h2>👤 {user.email}</h2>
      <p>권한: {user.role}</p>

      {user.role === 'ADMIN' && <p>🔐 관리자 전용 기능 표시</p>}
      {user.role === 'USER' && <p>👋 일반 사용자 기능 표시</p>}
    </div>
  );
}