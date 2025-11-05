import './App.css'
import { Routes, Route } from 'react-router-dom'
import { ThemeProvider } from './components/theme-provider'
import { ModeToggle } from './components/mode-toggle'

// 페이지 컴포넌트
import Home from './pages/Home'
import Login from './pages/Login'
import UserDashboard from './pages/user/Dashboard'
import AdminDashboard from './pages/admin/Dashboard'
import PartnerDashboard from './pages/partner/Dashboard'
import NotFound from './pages/NotFound'
import { Toaster } from 'react-hot-toast'

function App() {
  return (
    <ThemeProvider defaultTheme="dark" storageKey="vite-ui-theme">
      <Toaster position="top-center" />
      <div className="bg-background text-foreground min-h-screen">
        <ModeToggle />
        <Routes>
          {/* 공통 */}
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="*" element={<NotFound />} />

          {/* 사용자 */}
          <Route path="/user/dashboard" element={<UserDashboard />} />

          {/* 관리자 */}
          <Route path="/admin/dashboard" element={<AdminDashboard />} />

          {/* 파트너 */}
          <Route path="/partner/dashboard" element={<PartnerDashboard />} />
        </Routes>
      </div>
    </ThemeProvider>
  )
}

export default App