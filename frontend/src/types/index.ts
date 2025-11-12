export interface OnbidItemSearchCondition {
  sido?: string;
  sgk?: string;
  emd?: string;
  cltrNm?: string;
  cltrMnmtNo?: string;
  pbctCltrStatNm?: string;
  minBidPrcFrom?: number;
  minBidPrcTo?: number;
  apslAsesAvgAmtFrom?: number;
  apslAsesAvgAmtTo?: number;
  pbctBegnDtmFrom?: string;
  pbctBegnDtmTo?: string;
}

export interface OnbidItemResponse {
  cltrMnmtNo: string;
  cltrNm: string;
  apslAsesAvgAmt: number;
  minBidPrc: number;
  pbctBegnDtm: string;
  pbctClsDtm: string;
  pbctCltrStatNm: string;
}

export interface PageResponse<T> {
  content: T[];
  page: number;
  size: number;
  totalElements: number;
  totalPages: number;
}