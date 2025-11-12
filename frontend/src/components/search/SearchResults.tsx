import Pagination from "../Pagination";
import AuctionCard from "../AuctionCard";
import type { OnbidItemResponse } from "@/types";

interface Props {
  lists: OnbidItemResponse[];
  page: number;
  totalPages: number;
  onPageChange: (page: number) => void;
}

const SearchResults = ({ lists, page, totalPages, onPageChange }: Props) => {
  const hasResults = lists.length > 0;

  return (
    <>
      {hasResults ? (
        <>
          {lists.map((item, index) => (
            <AuctionCard key={`${item.cltrMnmtNo}-${index}`} {...item} />
          ))}
          <Pagination
            currentPage={page}
            totalPages={totalPages}
            onPageChange={onPageChange}
          />
        </>
      ) : (
        <div className="text-center text-gray-500 mt-4">검색 결과가 없습니다.</div>
      )}
    </>
  );
};

export default SearchResults;