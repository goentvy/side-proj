import './App.css'
import { Routes, Route } from 'react-router-dom'
import { ThemeProvider } from './components/theme-provider'
import { Toaster } from 'react-hot-toast'
import { SecureRoute } from './routes'
import { useEffect } from 'react'
import { useAuthStore } from './store/authStore'

// 페이지 컴포넌트
import Login from './pages/Login'
import NotFound from './pages/NotFound'

// 대시보드
import UserDashboard from './pages/user/UserDashboard'
import AdminDashboard from './pages/admin/AdminDashboard'
import PartnerDashboard from './pages/partner/PartnerDashboard'
import UnauthorizedPage from './pages/UnauthorizedPage'
import SearchPage from './pages/search/SearchPage'
import Header from './components/Header'

function App() {

  useEffect(() => {
    useAuthStore.getState().fetchUser();
  }, []);

  return (
    <ThemeProvider defaultTheme="dark" storageKey="vite-ui-theme">
      <Toaster position="top-center" />
      <div className="bg-background text-foreground min-h-screen">
        <Header />

        <Routes>
          {/* 공통 */}
          <Route path="/" element={<SearchPage />} />
          <Route path="/login" element={<Login />} />
          <Route path="/unauthorized" element={<UnauthorizedPage />} />
          <Route path="*" element={<NotFound />} />

          {/* 대시보드 */}
          <Route 
            path="/user/dashboard" 
            element={
              <SecureRoute allowedRoles={['USER']}>
                <UserDashboard />
              </SecureRoute>
            }
          />
          <Route 
            path="/admin/dashboard" 
            element={
              <SecureRoute allowedRoles={['ADMIN']}>
                <AdminDashboard />
              </SecureRoute>
            }
          />
          <Route 
            path="/partner/dashboard" 
            element={
              <SecureRoute allowedRoles={['PARTNER']}>
                <PartnerDashboard />
              </SecureRoute>
            }
          />
        </Routes>
      </div>
    </ThemeProvider>
  )
}

export default App