import SearchForm from "@/components/search/SearchForm";
import SearchResults from "@/components/search/SearchResults";
import type { OnbidItemSearchCondition, OnbidItemResponse } from "@/types";
import { useEffect, useState } from "react";
import api from "@/api/api";
import toast from "react-hot-toast";

const SearchPage = () => {
  const [allItems, setAllItems] = useState<OnbidItemResponse[]>([]);
  const [cond, setCond] = useState<OnbidItemSearchCondition | null>(null);

  // 초기 전체 데이터 로딩
  useEffect(() => {
    api.get("/cards")
    .then(res => {
      setAllItems(res.data.content);
    })
    .catch(err => {
      toast.error("전체 물건 조회 실패");
      console.error("전체 물건 조회 실패: ", err);
    });
  }, []);

  return (
    <div>
      <SearchForm onSubmit={setCond} />
      <SearchResults cond={cond} lists={allItems} />
    </div>
  );
};

export default SearchPage;