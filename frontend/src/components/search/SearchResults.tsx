import { useEffect, useState } from "react";
import axios from "axios";
import Pagination from "../Pagination";
import AuctionCard from "../AuctionCard";
import LoadingSpinner from "../LoadingSpinner";
import type { OnbidItemResponse, OnbidItemSearchCondition } from "@/types";

interface Props {
  cond: OnbidItemSearchCondition | null;
  lists: OnbidItemResponse[]; 
}

const SearchResults = ({ cond, lists }: Props) => {
  const [showAll, setShowAll] = useState(false);
  const [items, setItems] = useState<OnbidItemResponse[]>([]);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(1);
  const [isLoading, setIsLoading] = useState(false);
  const ITEMS_PER_PAGE = 10;

  useEffect(() => {
    if (!showAll) return;

    const fetchItems = async () => {
      setIsLoading(true);

      try {
        const { data } = await axios.get("/api/onbid/cards");
        setItems(data.content);
        console.log(data);
        setTotalPages(Math.ceil(data.totalElements / ITEMS_PER_PAGE));
      } catch (error) {
        console.error("API Error:", error);
      } finally {
        setIsLoading(false);
      }
    };

    fetchItems();
  }, [showAll, page]);

  const paginatedItems = items.slice(
    page * ITEMS_PER_PAGE,
    (page + 1) * ITEMS_PER_PAGE
  );

  const hasResults = cond ? lists.length > 0 : paginatedItems.length > 0;

  const safeLists = Array.isArray(lists) ? lists : [];

  if (isLoading) return <LoadingSpinner />;
  console.log(items.length);

  return (
    <>
      {!cond && !showAll && (
        <button onClick={() => {
          setPage(0);
          setShowAll(true);
        }} 
          className="btn"
        >
          모든 데이터 보기
        </button>
      )} 
      {cond 
        ? safeLists.map((list, index) => (
          <AuctionCard key={`${list.cltrMnmtNo}-${index}`} {...list} />
        )) 
        : paginatedItems.map(item => (
          <AuctionCard key={item.cltrMnmtNo} {...item} />
        ))}
      {hasResults ? (
        <Pagination
          currentPage={page}
          totalPages={totalPages}
          onPageChange={setPage}
        />
      ) : (
        <div className="text-center text-gray-500 mt-4">검색 결과가 없습니다.</div>
      )}
    </>
  );
};

export default SearchResults;