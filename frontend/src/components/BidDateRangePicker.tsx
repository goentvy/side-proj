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
    <div style={{ display: "flex", gap: "1rem", flexWrap: "wrap" }}>
      <div>
        <label>입찰 시작일</label>
        <DatePicker
          selected={pbctBegnDtmFrom}
          onChange={onChangeStart}
          showTimeSelect
          timeFormat="HH:mm"
          timeIntervals={15}
          dateFormat="yyyy-MM-dd'T'HH:mm:ss"
          placeholderText="입찰 시작일 선택"
        />
      </div>
      <div>
        <label>입찰 종료일</label>
        <DatePicker
          selected={pbctBegnDtmTo}
          onChange={onChangeEnd}
          showTimeSelect
          timeFormat="HH:mm"
          timeIntervals={15}
          dateFormat="yyyy-MM-dd'T'HH:mm:ss"
          placeholderText="입찰 종료일 선택"
          minDate={pbctBegnDtmFrom || undefined}
        />
      </div>
    </div>
  );
}