import api from './api';
import type { OnbidItemSearchCondition, PageResponse, OnbidItemResponse } from '@/types';

export const searchItems = async (
  cond: OnbidItemSearchCondition,
  page: number = 0,
  size: number = 10
): Promise<PageResponse<OnbidItemResponse>> => {
  const response = await api.post('/items/search', cond, {
    params: { page, size },
  });
  return response.data;
};