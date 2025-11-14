import DashboardLayout from "@/components/dashboard/DashboardLayout";
import { useAuthStore } from "@/store/authStore";
import AdminDashboard from "./AdminDashboard";
import PartnerDashboard from "./PartnerDashboard";
import UserDashboard from "./UserDashboard";

const DashboardPage = () => {
  const { user } = useAuthStore();

  const renderContent = () => {
    switch (user?.role) {
      case "ADMIN":
        return <AdminDashboard />;
      case "PARTNER":
        return <PartnerDashboard />;
      default:
        return <UserDashboard />;
    }
  };

  return <DashboardLayout>{renderContent()}</DashboardLayout>;
};

export default DashboardPage;