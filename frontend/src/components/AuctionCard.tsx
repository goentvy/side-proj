import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Badge } from "@/components/ui";
import type { OnbidItemResponse } from "@/types";
import { useNavigate } from "react-router-dom";

function formatCurrency(value: number | null | undefined) {
  if (value == null) return "-";
  return value.toLocaleString();
}

const AuctionCard = ({
  cltrMnmtNo,
  cltrNm,
  apslAsesAvgAmt,
  minBidPrc,
  pbctBegnDtm,
  pbctClsDtm,
  pbctCltrStatNm,
}: OnbidItemResponse) => {
  const navigate = useNavigate();

  return (
    <Card
      className="mb-4 shadow-sm cursor-pointer hover:shadow-md transition"
      onClick={() => navigate(`/item/${cltrMnmtNo}`)}
    >
      <CardHeader>
        <CardTitle className="text-lg font-semibold">{cltrNm}</CardTitle>
        <Badge variant={pbctCltrStatNm === "입찰중" ? "default" : "secondary"}>
          {pbctCltrStatNm}
        </Badge>
      </CardHeader>
      <CardContent className="space-y-1 text-sm text-muted-foreground">
        <div>💰 감정가: {formatCurrency(apslAsesAvgAmt)}</div>
        <div>🔓 입찰가: {formatCurrency(minBidPrc)}</div>
        <div>📅 입찰기간: {pbctBegnDtm} ~ {pbctClsDtm}</div>
        <div>🆔 관리번호: {cltrMnmtNo}</div>
      </CardContent>
    </Card>
  );
};

export default AuctionCard;