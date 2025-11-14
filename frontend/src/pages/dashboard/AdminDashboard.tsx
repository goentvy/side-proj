import { RegionBidChart } from "@/components/dashboard/RegionBidChart";

export default function AdminDashboard() {
  return (
      <div className="mt-6 space-y-4">
        <h2 className="text-lg font-semibold">입찰 수량 집계 그래프</h2>
        {/* 입찰 수량 집계 그래프 */}
        <RegionBidChart />
        <h2 className="text-lg font-semibold">승인 대기 입찰</h2>
        {/* 입찰 승인 리스트 컴포넌트 */}

        <h2 className="text-lg font-semibold">사용자 활동 로그</h2>
        {/* 사용자 로그 테이블 컴포넌트 */}

        <h2 className="text-lg font-semibold">시스템 통계</h2>
        {/* 전체 입찰 수, 사용자 수, 파트너 수 등 요약 카드 */}
      </div>
  );
}