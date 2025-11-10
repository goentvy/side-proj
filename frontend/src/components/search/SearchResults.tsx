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
  const [items, setItems] = useState<OnbidItemResponse[]>([]);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(1);
  const [isLoading, setIsLoading] = useState(false);

  useEffect(() => {
    const fetchItems = async () => {
      setIsLoading(true);

      try {
        const { data } = await axios.post("/api/onbid/items/search", cond, {
          params: {
            page: page,
            size: 10,
          },
        });

        setItems(data.content);
        setTotalPages(data.totalPages);
      } catch (error) {
        console.error("API Error:", error);
      } finally {
        setIsLoading(false);
      }
    };

    fetchItems();
  }, [cond, page]);

  if (isLoading) return <LoadingSpinner />;

  return (
    <>
      {items.length > 0 ? items.map(item => (
        <AuctionCard key={item.cltrMnmtNo} {...item} />
      )) : lists.map((list, index) => (
        <AuctionCard key={`${list.cltrMnmtNo}-${index}`} {...list} />
      ))}
      {(cond && items.length === 0) && (
        <div className="text-center text-gray-500 mt-4">검색 결과가 없습니다.</div>
      )}
      {items.length > 0 && (
        <Pagination
          currentPage={page}
          totalPages={totalPages}
          onPageChange={setPage}
        />
      )}
    </>
  );
};

export default SearchResults;