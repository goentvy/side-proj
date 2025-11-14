import type { OnbidItemSearchCondition } from "@/types";

export const ConditionLabels: Record<keyof OnbidItemSearchCondition, string> = {
  sido: "시도",
  sgk: "시군구",
  emd: "읍면동",
  cltrNm: "물건명",
  cltrMnmtNo: "관리번호",
  pbctCltrStatNm: "입찰상태",
  minBidPrcFrom: "최저 입찰가",
  minBidPrcTo: "최고 입찰가",
  apslAsesAvgAmtFrom: "감정가 시작",
  apslAsesAvgAmtTo: "감정가 끝",
  pbctBegnDtmFrom: "입찰 시작일",
  pbctBegnDtmTo: "입찰 종료일",
};
