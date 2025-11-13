import { useAuthStore } from "@/store/authStore";

export default function UserDashboard() {
  const { user } = useAuthStore();
  return (
    <div>
      <h1>사용자 대시보드</h1>
      <h2>👤 {user?.email}</h2>
      <p>권한: {user?.role}</p>
    </div>
  );
}