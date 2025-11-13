import React from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

type Props = {
  pbctBegnDtmFrom: Date | null;
  pbctBegnDtmTo: Date | null;
  onChangeStart: (date: Date | null) => void;
  onChangeEnd: (date: Date | null) => void;
};

export default function BidDateRangePicker({
  pbctBegnDtmFrom,
  pbctBegnDtmTo,
  onChangeStart,
  onChangeEnd,
}: Props) {
  return (
    <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
      <div className="flex flex-col">
        <label className="text-sm font-medium text-gray-700 mb-1">입찰 시작일</label>
        <DatePicker
          selected={pbctBegnDtmFrom}
          onChange={onChangeStart}
          showTimeSelect
          timeFormat="HH:mm"
          timeIntervals={15}
          dateFormat="yyyy-MM-dd'T'HH:mm:ss"
          placeholderText="시작일 선택"
          className="w-full border border-gray-300 rounded-md px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
        />
      </div>
      <div className="flex flex-col">
        <label className="text-sm font-medium text-gray-700 mb-1">입찰 종료일</label>
        <DatePicker
          selected={pbctBegnDtmTo}
          onChange={onChangeEnd}
          showTimeSelect
          timeFormat="HH:mm"
          timeIntervals={15}
          dateFormat="yyyy-MM-dd'T'HH:mm:ss"
          placeholderText="종료일 선택"
          minDate={pbctBegnDtmFrom || undefined}
          className="w-full border border-gray-300 rounded-md px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-purple-500"
        />
      </div>
    </div>
  );
}