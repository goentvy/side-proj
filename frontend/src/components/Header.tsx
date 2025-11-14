import { useNavigate } from 'react-router-dom';
import { ModeToggle } from './mode-toggle';
import { useAuthStore } from '@/store/authStore';

export default function Header() {
  const navigate = useNavigate();
  const { user, logout } = useAuthStore();

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  return (
    <header className="flex items-center justify-between px-6 py-4 bg-muted text-muted-foreground shadow-sm">
      <div className="flex items-center gap-4">
        <h1 className="text-xl font-bold cursor-pointer" onClick={() => navigate('/')}>
          🏛 OpenBidHub
        </h1>
        {user && (
          <button onClick={() => navigate('/')} className="text-sm hover:underline">
            내 대시보드
          </button>
        )}
        <button onClick={() => navigate('/search')} className="text-sm hover:underline">
          조건 검색
        </button>
      </div>

      <div className="flex items-center gap-4">
        <ModeToggle />
        {user && (
          <>
            <span className="text-sm">{user.email} ({user.role})</span>
            <button onClick={handleLogout} className="text-sm text-red-500 hover:underline">
              로그아웃
            </button>
          </>
        )}
      </div>
    </header>
  );
}