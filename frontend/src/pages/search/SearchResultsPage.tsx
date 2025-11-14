import { useSearchParams } from "react-router-dom";
import { useEffect, useState } from "react";
import SearchResults from "@/components/search/SearchResults";
import api from "@/api/apiAuction";
import toast from "react-hot-toast";
import { formatCltrMnmtNo } from "@/lib/date";
import type { OnbidItemResponse } from "@/types";
import { RenderConditionSummary } from "@/components/search/RenderConditionSummary";

const SearchResultsPage = () => {
  const [params] = useSearchParams();
  const [results, setResults] = useState<OnbidItemResponse[]>([]);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(1);
  const ITEMS_PER_PAGE = 10;

  const queryCond = Object.fromEntries(params.entries());
  const isEmptyQuery = Object.keys(queryCond).length === 0;

  useEffect(() => {
    const fetchData = async () => {
      const searchParams = new URLSearchParams();
      searchParams.append("page", String(page));
      searchParams.append("size", String(ITEMS_PER_PAGE));

      Object.entries(queryCond).forEach(([key, value]) => {
        const formatted = key === "cltrMnmtNo" ? formatCltrMnmtNo(value) : value;
        searchParams.append(key, formatted);
      });

      try {
        const endpoint = isEmptyQuery ? "/onbid/cards" : "/auction-items";
        const res = await api.get(`${endpoint}?${searchParams.toString()}`);
        setResults(res.data.content);
        setTotalPages(res.data.totalPages);
      } catch (err) {
        toast.error("검색 결과를 불러오지 못했습니다.");
        console.error(err);
      }
    };

    fetchData();
  }, [params, page]);

  return (
    <div className="p-6">
      <h2 className="text-xl font-semibold mb-4">
        {isEmptyQuery ? "전체 입찰 목록" : "검색 결과"}
      </h2>
      {!isEmptyQuery && RenderConditionSummary(queryCond)}
      <SearchResults 
        lists={results} 
        page={page} 
        totalPages={totalPages} 
        onPageChange={setPage} 
      />
    </div>
  );
};

export default SearchResultsPage;