import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Input, Button, Card, CardContent, CardHeader, CardTitle } from '@/components/ui';
import toast from 'react-hot-toast';
import axios from '@/lib/axios';
import { useAuthStore } from '@/store/authStore';

export default function Login() {
  const navigate = useNavigate();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const { login } = useAuthStore();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const res = await axios.post('/api/login', { email, password });
      const token = res.data.token;
      localStorage.setItem('token', token);
      login(res.data.token);

      navigate('/');
    } catch (err) {
      toast.error('로그인 실패');
      console.error("로그인 실패: " + err);
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-100">
      <Card className="w-full max-w-md shadow-lg">
        <CardHeader>
          <CardTitle className="text-center text-2xl">OpenBidHub 로그인</CardTitle>
        </CardHeader>
        <CardContent className="space-y-4">
          <Input
            type="email"
            placeholder="이메일"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <Input
            type="password"
            placeholder="비밀번호"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          <Button className="w-full" onClick={handleSubmit}>
            로그인
          </Button>
        </CardContent>
      </Card>
    </div>
  );
}