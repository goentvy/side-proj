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
import UnauthorizedPage from './pages/UnauthorizedPage'

// 공용 레이아웃
import Header from './components/Header'
import Footer from './components/Footer'

// 대시보드
import DashboardPage from './pages/dashboard/DashboardPage'

// 검색 페이지
import SearchFormPage from './pages/search/SearchFormPage'
import SearchResultsPage from './pages/search/SearchResultsPage'

// 상세보기 페이지
import ItemDetailPage from './pages/item/ItemDetailPage'

function App() {

  useEffect(() => {
    useAuthStore.getState().fetchUser();
  }, []);

  return (
    <ThemeProvider defaultTheme="dark" storageKey="vite-ui-theme">
      <Toaster position="top-right" />
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
          <Route 
            path="/item/:cltrMnmtNo" 
            element={
              <SecureRoute>
                <ItemDetailPage />
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