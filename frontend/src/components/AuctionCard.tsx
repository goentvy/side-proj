import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Badge } from "@/components/ui";
import type { OnbidItemResponse } from "@/types";

function formatCurrency(value: number | null | undefined) {
  if (value == null) return "-"; // 또는 "정보 없음"
  return value.toLocaleString();
}

function formatDate(date: string | null | undefined) {
  if (!date) return "-"; // 또는 "날짜 없음"
  return date.slice(0, 10);
}

const AuctionCard = ({
  cltrMnmtNo,
  cltrNm,
  sido,
  sgk,
  emd,
  goodsPrice,
  openPrice,
  pbctBegnDt,
  pbctClsDt,
  bidStatus,
}: OnbidItemResponse) => {
  return (
    <Card className="mb-4 shadow-sm">
      <CardHeader>
        <CardTitle className="text-lg font-semibold">{cltrNm}</CardTitle>
        <Badge variant={bidStatus === "입찰중" ? "default" : "secondary"}>
          {bidStatus}
        </Badge>
      </CardHeader>
      <CardContent className="space-y-1 text-sm text-muted-foreground">
        <div>📍 {sido} {sgk} {emd}</div>
        <div>💰 감정가: {formatCurrency(goodsPrice)}</div>
        <div>🔓 입찰가: {formatCurrency(openPrice)}</div>
        <div>📅 입찰기간: {formatDate(pbctBegnDt)} ~ {formatDate(pbctClsDt)}</div>
        <div>🆔 관리번호: {cltrMnmtNo}</div>
      </CardContent>
    </Card>
  );
};

export default AuctionCard;