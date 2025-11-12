import SearchForm from "@/components/search/SearchForm";
import SearchResults from "@/components/search/SearchResults";
import type { OnbidItemSearchCondition, OnbidItemResponse } from "@/types";
import { useEffect, useState } from "react";
import api from "@/api/apiAuction";
import toast from "react-hot-toast";
import { formatCltrMnmtNo } from "@/lib/date";

const SearchPage = () => {
  const [userRequestedAll, setUserRequestedAll] = useState<boolean>(false);
  const [filtered, setFiltered] = useState<OnbidItemResponse[]>([]);
  const [cond, setCond] = useState<OnbidItemSearchCondition | null>(null);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(1);
  const ITEMS_PER_PAGE = 10;

  const handleShowAll = () => {
    setCond(null);
    setUserRequestedAll(true);
    setPage(0);
  }

  useEffect(() => {
    const fetchData = async () => {
      const params = new URLSearchParams();
      params.append("page", String(page));
      params.append("size", String(ITEMS_PER_PAGE));

      if (cond) {
        Object.entries(cond).forEach(([key, value]) => {
          if (value !== undefined && value !== "") {
            const formattedValue = key === "cltrMnmtNo" ? formatCltrMnmtNo(String(value)) : String(value);
            params.append(key, formattedValue);
          }
        });
      }

      try {
        const endpoint = cond ? "/auction-items" : "/onbid/cards";
        const res = await api.get(`${endpoint}?${params.toString()}`);
        setFiltered(res.data.content);
        setTotalPages(res.data.totalPages);
        setUserRequestedAll(true);
      } catch (err) {
        toast.error("데이터 불러오기 실패");
        console.error("데이터 불러오기 실패: ", err);
      }
    };
    if (cond === null && !userRequestedAll) return;
    fetchData();
  }, [cond, page, userRequestedAll]);

  return (
    <div>
      <SearchForm onSubmit={setCond} onShowAll={handleShowAll} />
      {filtered.length === 0 && !cond && !userRequestedAll ? (
        <div className="text-center text-gray-400 mt-6">검색 조건을 입력하거나 전체 보기를 눌러주세요.</div>
      ): (
        <SearchResults
          lists={filtered}
          page={page}
          totalPages={totalPages}
          onPageChange={setPage}
        />
      )}
      
    </div>
  );
};

export default SearchPage;