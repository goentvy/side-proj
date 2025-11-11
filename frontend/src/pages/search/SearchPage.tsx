import SearchForm from "@/components/search/SearchForm";
import SearchResults from "@/components/search/SearchResults";
import type { OnbidItemSearchCondition, OnbidItemResponse } from "@/types";
import { useEffect, useState } from "react";
import api from "@/api/apiAuction";
import toast from "react-hot-toast";
import { formatCltrMnmtNo } from "@/lib/date";

const SearchPage = () => {
  const [filtered, setFiltered] = useState<OnbidItemResponse[]>([]);
  const [cond, setCond] = useState<OnbidItemSearchCondition | null>(null);

  // 조건이 변경되면 백엔드에 요청
  useEffect(() => {
    if (!cond) return;

    const params = new URLSearchParams();

    Object.entries(cond).forEach(([key, value]) => {
      if (value !== undefined && value !== "") {
        const formattedValue = key === "cltrMnmtNo" ? formatCltrMnmtNo(String(value)) : String(value);
        params.append(key, formattedValue);
      }
    });

    api.get(`/auction-items?${params.toString()}`)
      .then(res => {
        setFiltered(res.data.content);
      })
      .catch(err => {
        toast.error("조건 검색 실패");
        console.error("조건 검색 실패: ", err);
      });
  }, [cond]);

  return (
    <div>
      <SearchForm onSubmit={setCond} />
      <SearchResults cond={cond} lists={filtered} />
    </div>
  );
};

export default SearchPage;
