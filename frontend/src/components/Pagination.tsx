import { Button } from "@/components/ui";

interface Props {
  currentPage: number;
  totalPages: number;
  onPageChange: (page: number) => void;
}

const Pagination = ({ currentPage, totalPages, onPageChange }: Props) => {
  const prevDisabled = currentPage <= 0;
  const nextDisabled = currentPage >= totalPages - 1;

  return (
    <div className="flex items-center justify-center gap-2 mt-4">
      <Button
        variant="outline"
        disabled={prevDisabled}
        onClick={() => onPageChange(currentPage - 1)}
      >
        이전
      </Button>

      <span className="text-sm text-muted-foreground">
        {currentPage + 1} / {totalPages}
      </span>

      <Button
        variant="outline"
        disabled={nextDisabled}
        onClick={() => onPageChange(currentPage + 1)}
      >
        다음
      </Button>
    </div>
  );
};

export default Pagination;