export interface OnbidItemSearchCondition {
  sido?: string;
  sgk?: string;
  emd?: string;
  cltrNm?: string;
  openPriceFrom?: number;
  openPriceTo?: number;
  goodsPriceFrom?: number;
  goodsPriceTo?: number;
  bidStatus?: string;
  pbctBegnDtFrom?: string;
  pbctBegnDtTo?: string;
  cltrMnmtNo?: string;
}

export interface OnbidItemResponse {
  cltrMnmtNo: string;
  cltrNm: string;
  sido: string;
  sgk: string;
  emd: string;
  goodsPrice: number;
  openPrice: number;
  pbctBegnDt: string;
  pbctClsDt: string;
  bidStatus: string;
}

export interface PageResponse<T> {
  content: T[];
  page: number;
  size: number;
  totalElements: number;
  totalPages: number;
}