import type { User } from "@/types/user";

export default function UserDashboard({ user }: User) {
  return (
    <div>
      <h1>사용자 대시보드</h1>
      <p>환영합니다, {user.email} ({user.role})</p>
    </div>
  );
}