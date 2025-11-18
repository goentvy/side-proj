import apiAuction from "@/api/apiAuction";
import { useQuery } from "@tanstack/react-query";
import { useNavigate } from "react-router-dom";

export function SummaryCards({ role }: { role?: string }) {

  const { data } = useQuery({
    queryKey: ["bidStatusSummary"],
    queryFn: async () => {
      const res = await apiAuction.get("/auction-items/bid-status-summary")
      return res.data
    },
  });
  
  const cards = [
    { title: '입찰준비중', value: data?.["입찰준비중"] ?? 0 },
    { title: '인터넷입찰진행중', value: data?.["인터넷입찰진행중"] ?? 0 },
    ...(role === "admin" ? [{ title: '알림', value: 5 }] : []),
  ];

  const navigate = useNavigate();

  const handleCardClick = (status: string) => {
    if(status === "알림" && role !== "admin") return;
    navigate(`/search/results?pbctCltrStatNm=${encodeURIComponent(status)}`);
  };

  return (
    <div className="grid grid-cols-3 gap-4">
      {cards.map((card) => (
        <div
          key={card.title}
          className={`bg-white hover:bg-gray-100 cursor-pointer p-4 rounded-lg shadow transition-all duration-200 border
            ${role === "admin" ? "border-blue-400" : "border-gray-200"}`}
          onClick={() => handleCardClick(card.title)}
        >
          <div className="text-sm text-gray-500">{card.title}</div>
          <div className="text-2xl font-bold text-gray-800">{card.value}</div>
        </div>
      ))}
    </div>
  );
}