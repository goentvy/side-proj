import { useQuery } from '@tanstack/react-query';
import { searchItems } from '@/api/searchItems';
import type { OnbidItemSearchCondition, PageResponse, OnbidItemResponse } from '@/types';

export const useSearchItems = (
  cond: OnbidItemSearchCondition,
  page: number,
  size: number = 10
) => {
  return useQuery<PageResponse<OnbidItemResponse>>({
    queryKey: ['searchItems', cond, page, size],
    queryFn: () => searchItems(cond, page, size),
    enabled: !!cond,
  });
};