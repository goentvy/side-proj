import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { useMutation } from '@tanstack/react-query'
import { loginUser } from '@/api/auth'
import { Input, Button, Card, CardContent, CardHeader, CardTitle } from '@/components/ui'
import toast from 'react-hot-toast'

export default function Login() {
  const navigate = useNavigate()
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')

  const { mutate, isLoading } = useMutation({
    mutationFn: () => loginUser(email, password),
    onSuccess: (data) => {
      const role = data.role
      toast.success(`${role} 로그인 성공!`)
      if (role === 'admin') navigate('/admin/dashboard')
      else if (role === 'user') navigate('/user/dashboard')
      else if (role === 'partner') navigate('/partner/dashboard')
      else toast.error('알 수 없는 역할입니다')
    },
    onError: () => {
      toast.error('로그인 실패: 이메일 또는 비밀번호를 확인하세요')
    },
  })

  const handleLogin = async () => {
    // TODO: Replace with actual login API call
    const role = await mockLogin(email, password)

    if (role === 'admin') {
      toast.success('관리자 로그인 성공!')
      navigate('/admin/dashboard')
    } else if (role === 'user') {
      toast.success('사용자 로그인 성공!')
      navigate('/user/dashboard')
    } else if (role === 'partner') {
      toast.success('파트너 로그인 성공!')
      navigate('/partner/dashboard')
    } else toast.error('로그인 실패: 권한이 없거나 정보가 잘못되었습니다')
  }

  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-100">
      <Card className="w-full max-w-md shadow-lg">
        <CardHeader>
          <CardTitle className="text-center text-2xl">OnBidHub 로그인</CardTitle>
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
          <Button className="w-full" onClick={() => mutate()} disabled={isLoading}>
            {isLoading ? '로그인 중...' : '로그인'}
          </Button>
        </CardContent>
      </Card>
    </div>
  )
}

// 임시 로그인 로직 (테스트용)
async function mockLogin(email: string, password: string): Promise<'admin' | 'user' | 'partner' | null> {
  if (email === 'admin@onbidhub.com') return 'admin'
  if (email === 'user@onbidhub.com') return 'user'
  if (email === 'partner@onbidhub.com') return 'partner'
  return null
}