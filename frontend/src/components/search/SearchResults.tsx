import { useSearchItems } from "@/hook/useSearchItems";
import { useState } from "react";
import Pagination from "../Pagination";
import AuctionCard from "../AuctionCard";
import LoadingSpinner from "../LoadingSpinner";
import type { OnbidItemSearchCondition } from "@/types";

interface Props {
    cond: OnbidItemSearchCondition;
}

const SearchResults = ({ cond }: Props) => {
  const [page, setPage] = useState(0);
  const { data, isLoading } = useSearchItems(cond, page);

  if (isLoading) return <LoadingSpinner />;

  return (
    <>
      {data?.content.map(item => (
        <AuctionCard key={item.cltrMnmtNo} {...item} />
      ))}
      {data && (
        <Pagination
            currentPage={data.page}
            totalPages={data.totalPages}
            onPageChange={setPage}
        />
      )}
    </>
  );
};

export default SearchResults;