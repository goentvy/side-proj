import { Button } from '@/components/ui';
import { useNavigate } from 'react-router-dom';

export default function UnauthorizedPage() {
  const navigate = useNavigate();

  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100">
      <h1 className="text-3xl font-bold mb-4">접근 권한이 없습니다</h1>
      <p className="mb-6 text-gray-600">이 페이지에 접근할 수 있는 권한이 없습니다.</p>
      <Button onClick={() => navigate('/')}>홈으로 돌아가기</Button>
    </div>
  );
}