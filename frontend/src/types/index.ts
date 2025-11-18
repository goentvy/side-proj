// 검색 조건 인터페이스
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

// 조건 검색 결과 인터페이스
export interface OnbidItemResponse {
  cltrMnmtNo: string;
  cltrNm: string;
  apslAsesAvgAmt: number;
  minBidPrc: number;
  pbctBegnDtm: string;
  pbctClsDtm: string;
  pbctCltrStatNm: string;
}

// 페이지 응답 인터페이스
export interface PageResponse<T> {
  content: T[];
  page: number;
  size: number;
  totalElements: number;
  totalPages: number;
}

// 물건 상세페이지 인터페이스 (ItemDetailPage)
export interface ItemDetail {
  cltrNm: string;
  ldnmAdrs: string;
  nmrdAdrs: string;
  cltrMnmtNo: string;
  ctgrFullNm: string;
  scrtNm: string;
  pbctNo: string;
  pbctCdtnNo: string;
  cltrNo: string;
  cltrHstrNo: string;
  minBidPrc: number;
  apslAsesAvgAmt: number;
  feeRate: string;
  pbctBegnDtm: string;
  pbctClsDtm: string;
  pbctCltrStatNm: string;
  uscbCnt: number;
  iqryCnt: number;
  goodsNm: string;
  tpbz: string;
  mmbRgtNm: string;
  cltrImgFileList: string[];
}