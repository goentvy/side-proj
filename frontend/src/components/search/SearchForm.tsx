import { useState } from "react";
import { Input, Button, Select, SelectTrigger, SelectValue, SelectContent, SelectItem } from "@/components/ui";
import type { OnbidItemSearchCondition } from "@/types";
import RegionSelect from "../RegionSelect";

interface Props {
  onSubmit: (cond: OnbidItemSearchCondition) => void;
}

const initialForm: OnbidItemSearchCondition = {
  sido: "",
  sgk: "",
  emd: "",
  cltrNm: "",
  openPriceFrom: undefined,
  openPriceTo: undefined,
  goodsPriceFrom: undefined,
  goodsPriceTo: undefined,
  bidStatus: "",
  pbctBegnDtFrom: "",
  pbctBegnDtTo: "",
  cltrMnmtNo: "",
};

const SearchForm = ({ onSubmit }: Props) => {
  const [form, setForm] = useState<OnbidItemSearchCondition>(initialForm);

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement>
  ) => {
    const { name, value, type } = e.target;
    setForm((prev) => ({
      ...prev,
      [name]: type === "number" ? Number(value) || undefined : value,
    }));
  };

  const handleSelectChange = (value: string) => {
    setForm((prev) => ({ ...prev, bidStatus: value === "all" ? "" : value, }));
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onSubmit(form);
  };

  return (
    <form onSubmit={handleSubmit} className="grid gap-4">
      <Input name="cltrNm" placeholder="물건명" value={form.cltrNm} onChange={handleChange} />
      <RegionSelect form={form} setForm={setForm} />

      <Input name="openPriceFrom" type="number" placeholder="최저 입찰가" value={form.openPriceFrom ?? ''} onChange={handleChange} />
      <Input name="openPriceTo" type="number" placeholder="최고 입찰가" value={form.openPriceTo ?? ''} onChange={handleChange} />

      <Input name="goodsPriceFrom" type="number" placeholder="감정가 시작" value={form.goodsPriceFrom ?? ''} onChange={handleChange} />
      <Input name="goodsPriceTo" type="number" placeholder="감정가 끝" value={form.goodsPriceTo ?? ''} onChange={handleChange} />

      <Select value={form.bidStatus} onValueChange={handleSelectChange}>
        <SelectTrigger>
          <SelectValue placeholder="입찰 상태" />
        </SelectTrigger>
        <SelectContent>
          <SelectItem value="all">전체</SelectItem>
          <SelectItem value="입찰중">입찰중</SelectItem>
          <SelectItem value="마감">마감</SelectItem>
        </SelectContent>
      </Select>

      <Input name="pbctBegnDtFrom" placeholder="입찰 시작일 (YYYYMMDD)" value={form.pbctBegnDtFrom} onChange={handleChange} />
      <Input name="pbctBegnDtTo" placeholder="입찰 종료일 (YYYYMMDD)" value={form.pbctBegnDtTo} onChange={handleChange} />

      <Input name="cltrMnmtNo" placeholder="관리번호" value={form.cltrMnmtNo} onChange={handleChange} />

      <Button type="submit">검색</Button>
    </form>
  );
};

export default SearchForm;