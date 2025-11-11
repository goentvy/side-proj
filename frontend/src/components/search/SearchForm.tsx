import { useMemo, useState } from "react";
import { Input, Button, Select, SelectTrigger, SelectValue, SelectContent, SelectItem } from "@/components/ui";
import type { OnbidItemSearchCondition } from "@/types";
import RegionSelect from "../RegionSelect";
import { formatToFullDate } from "@/lib/date";

interface Props {
  onSubmit: (cond: OnbidItemSearchCondition) => void;
}

const SearchForm = ({ onSubmit }: Props) => {
  const initialForm = useMemo<OnbidItemSearchCondition>(() => ({
    sido: "",
    sgk: "",
    emd: "",
    cltrNm: "",
    cltrMnmtNo: "",
    pbctCltrStatNm: "",
    minBidPrcFrom: undefined,
    minBidPrcTo: undefined,
    apslAsesAvgAmtFrom: undefined,
    apslAsesAvgAmtTo: undefined,
    pbctBegnDtmFrom: "",
    pbctBegnDtmTo: ""
  }), []);

  const [form, setForm] = useState<OnbidItemSearchCondition>(initialForm);

  const handleRegionChange = (name: string, value: string) => {
    setForm((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value, type } = e.target;
    const isFullDateField = name === "pbctBegnDtmFrom" || name === "pbctBegnDtmTo";
    const formatted = isFullDateField ? formatToFullDate(value) : value;

    setForm((prev) => ({
      ...prev,
      [name]: type === "number" ? Number(value) || undefined : formatted,
    }));
  };

  const handleSelectChange = (value: string) => {
    setForm((prev) => ({ ...prev, pbctCltrStatNm: value === "all" ? "" : value, }));
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onSubmit(form);
  };

  return (
    <form onSubmit={handleSubmit} className="grid gap-4">
      <Input name="cltrNm" placeholder="물건명" value={form.cltrNm} onChange={handleChange} />
      {/* <RegionSelect form={form} onChange={handleRegionChange} /> */}

      <Input name="minBidPrcFrom" type="number" placeholder="최저 입찰가" value={form.minBidPrcFrom ?? ''} onChange={handleChange} />
      <Input name="minBidPrcTo" type="number" placeholder="최고 입찰가" value={form.minBidPrcTo ?? ''} onChange={handleChange} />

      <Input name="apslAsesAvgAmtFrom" type="number" placeholder="감정가 시작" value={form.apslAsesAvgAmtFrom ?? ''} onChange={handleChange} />
      <Input name="apslAsesAvgAmtTo" type="number" placeholder="감정가 끝" value={form.apslAsesAvgAmtTo ?? ''} onChange={handleChange} />

      <Select value={form.pbctCltrStatNm} onValueChange={handleSelectChange}>
        <SelectTrigger>
          <SelectValue placeholder="입찰 상태" />
        </SelectTrigger>
        <SelectContent>
          <SelectItem value="all">전체</SelectItem>
          <SelectItem value="입찰중">입찰중</SelectItem>
          <SelectItem value="마감">마감</SelectItem>
        </SelectContent>
      </Select>

      <Input 
        name="pbctBegnDtmFrom" 
        placeholder="입찰 시작일 (예: 20250901140000)" 
        value={form.pbctBegnDtmFrom} 
        onChange={handleChange} />
      <Input 
        name="pbctBegnDtmTo" 
        placeholder="입찰 종료일 (예: 20250901140000)" 
        value={form.pbctBegnDtmTo} 
        onChange={handleChange} />

      <Input name="cltrMnmtNo" placeholder="관리번호 (예: 202509964001)" value={form.cltrMnmtNo} onChange={handleChange} />

      <Button type="submit">검색</Button>
    </form>
  );
};

export default SearchForm;