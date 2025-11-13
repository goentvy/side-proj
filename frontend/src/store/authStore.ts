import { create } from "zustand";
import { persist } from "zustand/middleware";
import axios from "axios";
import toast from "react-hot-toast";

export interface UserInfo {
    email: string;
    role: string;
}

interface AuthState {
    user: UserInfo | null;
    isLoading: boolean;
    login: (token: string) => void;
    logout: () => void;
    fetchUser: () => void;
}

export const useAuthStore = create(
    persist<AuthState>(
        (set) => ({
            user: null,
            isLoading: true,

            fetchUser: async () => {
                set({ isLoading: true });
                const token = localStorage.getItem("token");
                if (!token) {
                    set({ user: null, isLoading: false });
                    return;
                }

                try {
                    const res = await axios.get("/api/me", {
                        headers: { Authorization: `Bearer ${token}` },
                    });
                    set({
                        user: {
                            email: res.data.email,
                            role: res.data.role.trim().toUpperCase(),
                        },
                        isLoading: false,
                    });
                } catch (err) {
                    toast.error("세션이 만료되었습니다.");
                    console.error(err);
                    localStorage.removeItem("token");
                    set({ user: null, isLoading: false });
                }
            },

            login: async (token: string) => {
                localStorage.setItem("token", token);
                set({ isLoading: true });
                await useAuthStore.getState().fetchUser();
            },

            logout: () => {
                localStorage.removeItem("token");
                set({ user: null, isLoading: false });
            },
        }),
        {
            name: "auth-storage", // localStorage key 로그인 상태 유지
        }
    )
);
