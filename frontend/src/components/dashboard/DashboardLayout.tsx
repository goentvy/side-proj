import { useAuthStore } from "@/store/authStore";
import { GreetingCard } from "./GreetingCard";
import { SummaryCards } from "./SummaryCards";
import { RecentNotifications } from "./RecentNotifications";
import { QuickActions } from "./QuickActions";

export default function DashboardLayout({ children }: { children: React.ReactNode }) {
  const { user } = useAuthStore();

  return (
    <div className="p-6 space-y-6">
      <GreetingCard user={user} />
      <SummaryCards role={user?.role} />
      <RecentNotifications />
      <QuickActions role={user?.role} />
      {children}
    </div>
  );
}