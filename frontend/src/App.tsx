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
import UnauthorizedPage from './pages/UnauthorizedPage'
import Header from './components/Header'
import DashboardPage from './pages/dashboard/DashboardPage'
import SearchFormPage from './pages/search/SearchFormPage'
import SearchResultsPage from './pages/search/SearchResultsPage'
import Footer from './components/Footer'

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
          <Route 
            path="/" 
            element={
              <SecureRoute>
                <DashboardPage />
              </SecureRoute>
            }
          />
          <Route 
            path="/search" 
            element={
              <SecureRoute>
                <SearchFormPage />
              </SecureRoute>
            }
          />
          <Route 
            path="/search/results" 
            element={
              <SecureRoute>
                <SearchResultsPage />
              </SecureRoute>
            }
          />
          <Route path="/login" element={<Login />} />
          <Route path="/unauthorized" element={<UnauthorizedPage />} />
          <Route path="*" element={<NotFound />} />
        </Routes>
        <Footer />
      </div>
    </ThemeProvider>
  )
}

export default App