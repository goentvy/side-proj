import './App.css'
import { Routes, Route } from 'react-router-dom'
import { useEffect, useState } from 'react'
import { ThemeProvider } from './components/theme-provider'
import { ModeToggle } from './components/mode-toggle'
import { Toaster } from 'react-hot-toast'
import { SecureRoute } from './routes'

// 페이지 컴포넌트
import Home from './pages/Home'
import Login from './pages/Login'
import NotFound from './pages/NotFound'

// 대시보드
import UserDashboard from './pages/user/UserDashboard'
import AdminDashboard from './pages/admin/AdminDashboard'
import PartnerDashboard from './pages/partner/PartnerDashboard'
import UnauthorizedPage from './pages/UnauthorizedPage'


function App() {
  const [loggedIn, setLoggedIn] = useState(false);

  useEffect(() => {
    const token = localStorage.getItem('token');
    setLoggedIn(!!token);
  }, []);

  return (
    <ThemeProvider defaultTheme="dark" storageKey="vite-ui-theme">
      <Toaster position="top-center" />
      <div className="bg-background text-foreground min-h-screen">
        <ModeToggle />

        <Routes>
          {/* 공통 */}
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/unauthorized" element={<UnauthorizedPage />} />
          <Route path="*" element={<NotFound />} />

          {/* 대시보드 */}
          <Route 
            path="/user/dashboard" 
            element={
              <SecureRoute allowedRoles={['USER']}>
                {(user) => <UserDashboard user={user}/>}
              </SecureRoute>
            }
          />
          <Route 
            path="/admin/dashboard" 
            element={
              <SecureRoute allowedRoles={['ADMIN']}>
                {(user) => <AdminDashboard user={user}/>}
              </SecureRoute>
            }
          />
          <Route 
            path="/partner/dashboard" 
            element={
              <SecureRoute allowedRoles={['PARTNER']}>
                {(user) => <PartnerDashboard user={user}/>}
              </SecureRoute>
            }
          />
        </Routes>
      </div>
    </ThemeProvider>
  )
}

export default App