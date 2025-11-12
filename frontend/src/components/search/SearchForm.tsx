import { useMemo, useState } from "react";
import { Input, Button, Select, SelectTrigger, SelectValue, SelectContent, SelectItem } from "@/components/ui";
import type { OnbidItemSearchCondition } from "@/types";
import { formatToFullDate } from "@/lib/date";
import BidDateRangePicker from "../BidDateRangePicker";

interface Props {
  onSubmit: (cond: OnbidItemSearchCondition | null) => void;
  onShowAll: () => void;
}

const SearchForm = ({ onSubmit, onShowAll }: Props) => {
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

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value, type } = e.target;
    const isFullDateField = name === "pbctBegnDtmFrom" || name === "pbctBegnDtmTo";
    const formatted = isFullDateField ? formatToFullDate(value) : value;

    setForm((prev) => ({
      ...prev,
      [name]: type === "number" ? Number(value) || undefined : formatted,
    }));
  };

  const handleDateChange = (name: keyof OnbidItemSearchCondition, date: Date | null) => {
    setForm((prev) => ({
      ...prev,
      [name]: date ? date.toISOString().slice(0, 19) : "",
    }));
  };

  const handleSelectChange = (value: string) => {
    setForm((prev) => ({ ...prev, pbctCltrStatNm: value === "all" ? "" : value, }));
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    console.log("검색 조건: ", form);
    onSubmit(form);
  };

  const handleReset = () => {
    setForm(initialForm); // 입력 필드만 초기화
  };

  const handleShowAll = () => {
    setForm(initialForm);
    onShowAll();
  };

  return (
    <form onSubmit={handleSubmit} className="grid gap-4">
      <Input name="cltrNm" placeholder="물건명" value={form.cltrNm} onChange={handleChange} />

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
          <SelectItem value="입찰준비중">입찰준비중</SelectItem>
          <SelectItem value="인터넷입찰진행중">인터넷입찰진행중</SelectItem>
          <SelectItem value="마감">마감</SelectItem>
        </SelectContent>
      </Select>

      <BidDateRangePicker
        pbctBegnDtmFrom={form.pbctBegnDtmFrom ? new Date(form.pbctBegnDtmFrom) : null}
        pbctBegnDtmTo={form.pbctBegnDtmTo ? new Date(form.pbctBegnDtmTo) : null}
        onChangeStart={(date) => handleDateChange("pbctBegnDtmFrom", date)}
        onChangeEnd={(date) => handleDateChange("pbctBegnDtmTo", date)}
      />

      <Input name="cltrMnmtNo" placeholder="관리번호 (예: 202509964001)" value={form.cltrMnmtNo} onChange={handleChange} />

      <div className="flex flex-wrap gap-2 justify-start mt-4">
        <Button type="submit" className="bg-blue-600 text-white hover:bg-blue-700">
          🔍 검색
        </Button>
        <Button
          type="button"
          variant="outline"
          onClick={handleReset}
        >
          ↺ 초기화
        </Button>
        <Button
          type="button"
          className="bg-gradient-to-r from-indigo-500 to-purple-600 text-white hover:scale-105 transition-transform"
          onClick={handleShowAll}
        >
          📂 전체 데이터 보기
        </Button>
      </div>
    </form>
  );
};

export default SearchForm;