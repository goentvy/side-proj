import SearchForm from "@/components/search/SearchForm";
import SearchResults from "@/components/search/SearchResults";
import type { OnbidItemSearchCondition } from "@/types";
import { useState } from "react";

const SearchPage = () => {
  const [cond, setCond] = useState<OnbidItemSearchCondition | null>(null);

  return (
    <div>
      <SearchForm onSubmit={setCond} />
      {cond && <SearchResults cond={cond} />}
    </div>
  );
};

export default SearchPage;