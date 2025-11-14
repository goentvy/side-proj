import { create } from "zustand";
import { persist } from "zustand/middleware";
import axios from "@/lib/axios";
import toast from "react-hot-toast";

export interface UserInfo {
    email: string;
    role: string;
}

interface AuthState {
  user: UserInfo | null;
  isLoading: boolean;
  isAuthenticated: boolean;
  error: string | null;
  loadingMessage: string | null;
  login: (token: string) => void;
  logout: () => void;
  fetchUser: () => void;
}

export const useAuthStore = create(
    persist<AuthState>(
        (set) => ({
            user: null,
            isLoading: true,
            isAuthenticated: false,
            error: null,
            loadingMessage: "사용자 정보 불러오는 중...",

            fetchUser: async () => {
                set({ isLoading: true, loadingMessage: "사용자 정보 불러오는 중..." });
                const token = localStorage.getItem("token");
                if (!token) {
                    set({ 
                        user: null,
                        isLoading: false,
                        isAuthenticated: false,
                        error: "토큰 없음",
                        loadingMessage: null,
                    });
                    return;
                }

                try {
                    const res = await axios.get("/api/me");
                    set({
                        user: {
                            email: res.data.email,
                            role: res.data.role.trim().toUpperCase(),
                        },
                        isLoading: false,
                        isAuthenticated: true,
                        error: null,
                        loadingMessage: null,
                    });
                } catch (err) {
                    toast.error("세션이 만료되었습니다.");
                    console.error(err);
                    localStorage.removeItem("token");
                    set({ 
                        user: null,
                        isLoading: false,
                        isAuthenticated: false,
                        error: "세션이 만료되었습니다.",
                        loadingMessage: null,    
                    });
                }
            },

            login: async (token: string) => {
                localStorage.setItem("token", token);
                set({ isLoading: true });
                await useAuthStore.getState().fetchUser();
            },

            logout: () => {
                localStorage.removeItem("token");
                set({
                    user: null,
                    isLoading: false,
                    isAuthenticated: false,
                    error: null,
                    loadingMessage: null,
                });
            },
        }),
        {
            name: "auth-storage", // localStorage key 로그인 상태 유지
        }
    )
);
