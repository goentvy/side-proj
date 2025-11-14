import type { UserInfo } from "@/store/authStore";

export function GreetingCard({ user }: { user: UserInfo | null }) {
  const hour = new Date().getHours();
  const greeting =
    hour < 12 ? '좋은 아침입니다' : hour < 18 ? '안녕하세요' : '좋은 저녁입니다';

  return (
    <div className="text-xl font-semibold">
      {greeting}, {user?.email}님! ({user?.role})
    </div>
  );
}