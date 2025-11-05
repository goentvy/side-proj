import { useUser } from '../pages/hook/useUser';

export default function ProfilePage() {
  const user = useUser();

  if (!user) return <p>로딩 중...</p>;

  return (
    <div>
      <h2>👤 {user.email}</h2>
      <p>권한: {user.role}</p>

      {user.role === 'ADMIN' && <p>🔐 관리자 전용 기능 표시</p>}
      {user.role === 'USER' && <p>👋 일반 사용자 기능 표시</p>}
    </div>
  );
}