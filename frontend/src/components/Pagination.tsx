import { Button } from "@/components/ui";

interface Props {
  currentPage: number;
  totalPages: number;
  onPageChange: (page: number) => void;
}

const Pagination = ({ currentPage, totalPages, onPageChange }: Props) => {
  const visiblePages = 5;
  const startPage = Math.max(0, currentPage - Math.floor(visiblePages / 2));
  const endPage = Math.min(totalPages - 1, startPage + visiblePages - 1);

  const pages = [];
  for (let i = startPage; i <= endPage; i++) {
    pages.push(i);
  }

  return (
    <div className="flex items-center justify-center gap-2 mt-6">
      <Button
        variant="outline"
        disabled={currentPage === 0}
        onClick={() => onPageChange(currentPage - 1)}
      >
        이전
      </Button>

      {pages.map((page) => (
        <Button
          key={page}
          className={`btn ${page === currentPage ? "btn-active" : ""}`}
          onClick={() => onPageChange(page)}
        >
          {page + 1}
        </Button>
      ))}

      <Button
        variant="outline"
        disabled={currentPage === totalPages - 1}
        onClick={() => onPageChange(currentPage + 1)}
      >
        다음
      </Button>
    </div>
  );
};

export default Pagination;