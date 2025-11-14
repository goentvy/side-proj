import { RegionBidChart } from "@/components/dashboard/RegionBidChart";

export default function UserDashboard() {
  return (
      <div className="mt-6">
        <h2 className="text-lg font-semibold">입찰 수량 집계 그래프</h2>
        {/* 입찰 수량 집계 그래프 */}
        <RegionBidChart />
      </div>
  );
}