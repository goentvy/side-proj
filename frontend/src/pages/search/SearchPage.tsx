import SearchForm from "@/components/search/SearchForm";
import SearchResults from "@/components/search/SearchResults";
import type { OnbidItemSearchCondition, OnbidItemResponse } from "@/types";
import { useEffect, useState } from "react";
import api from "@/api/api";
import toast from "react-hot-toast";

const SearchPage = () => {
  const [allItems, setAllItems] = useState<OnbidItemResponse[]>([]);
  const [filtered, setFiltered] = useState<OnbidItemResponse[]>([]);
  const [cond, setCond] = useState<OnbidItemSearchCondition | null>(null);

  // 초기 전체 데이터 로딩
  useEffect(() => {
    api.get("/items")
    .then(res => {
      setAllItems(res.data.content);
      setFiltered(res.data.content);
    })
    .catch(err => {
      toast.error("전체 물건 조회 실패");
      console.error("전체 물건 조회 실패: ", err);
    });
  }, []);

  // 조건이 변경되면 필터링
  useEffect(() => {
  if (!cond) return;

  const filteredItems = allItems.filter(item => {
    // 시도, 시군구, 읍면동
    if (cond.sido && item.sido !== cond.sido) return false;
    if (cond.sgk && item.sgk !== cond.sgk) return false;
    if (cond.emd && item.emd !== cond.emd) return false;

    // 물건명 검색 (부분 일치)
    if (cond.cltrNm && !item.cltrNm.includes(cond.cltrNm)) return false;

    // 물건관리번호 (정확 일치)
    if (cond.cltrMnmtNo && item.cltrMnmtNo !== cond.cltrMnmtNo) return false;

    // 입찰 상태
    if (cond.bidStatus && item.bidStatus !== cond.bidStatus) return false;

    // 가격 조건
    if (cond.openPriceFrom && item.openPrice < cond.openPriceFrom) return false;
    if (cond.openPriceTo && item.openPrice > cond.openPriceTo) return false;
    if (cond.goodsPriceFrom && item.goodsPrice < cond.goodsPriceFrom) return false;
    if (cond.goodsPriceTo && item.goodsPrice > cond.goodsPriceTo) return false;

    // 입찰 시작일
    if (cond.pbctBegnDtFrom && item.pbctBegnDt < cond.pbctBegnDtFrom) return false;
    if (cond.pbctBegnDtTo && item.pbctBegnDt > cond.pbctBegnDtTo) return false;

    return true;
  });

  setFiltered(filteredItems);
}, [cond, allItems]);


  return (
    <div>
      <SearchForm onSubmit={setCond} />
      <SearchResults cond={cond} lists={filtered} />
    </div>
  );
};

export default SearchPage;