import SearchForm from "@/components/search/SearchForm";
import { useNavigate } from "react-router-dom";
import type { OnbidItemSearchCondition } from "@/types";

const SearchFormPage = () => {
  const navigate = useNavigate();

  const handleSubmit = (cond: OnbidItemSearchCondition | null) => {
    if(!cond) {
        navigate("/search/results");
        return;
    }
    const params = new URLSearchParams();
    Object.entries(cond).forEach(([key, value]) => {
      if (value) params.append(key, String(value));
    });
    navigate(`/search/results?${params.toString()}`);
  };

  const handleShowAll = () => {
    navigate("/search/results");
  };

  return (
    <div className="p-6">
      <h2 className="text-xl font-semibold mb-4">입찰 조건 검색</h2>
      <SearchForm onSubmit={handleSubmit} onShowAll={handleShowAll} />
    </div>
  );
};

export default SearchFormPage;