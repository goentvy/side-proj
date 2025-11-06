import { Select, SelectTrigger, SelectValue, SelectContent, SelectItem } from "@/components/ui";
import { regions } from "@/lib/regions";
import type { OnbidItemSearchCondition } from "@/types";

interface Props {
  form: OnbidItemSearchCondition;
  setForm: React.Dispatch<React.SetStateAction<OnbidItemSearchCondition>>;
}

const RegionSelect = ({ form, setForm }: Props) => {
  const selectedSido = regions.find(r => r.sido === form.sido);
  const selectedSigungu = selectedSido?.sigungu.find(s => s.name === form.sgk);

  return (
    <div className="grid gap-4">
      <Select value={form.sido} onValueChange={(value) => {
        setForm(prev => ({ ...prev, sido: value, sgk: "", emd: "" }));
      }}>
        <SelectTrigger><SelectValue placeholder="시도 선택" /></SelectTrigger>
        <SelectContent>
          {regions.map(r => (
            <SelectItem key={r.sido} value={r.sido}>{r.sido}</SelectItem>
          ))}
        </SelectContent>
      </Select>

      <Select value={form.sgk} onValueChange={(value) => {
        setForm(prev => ({ ...prev, sgk: value, emd: "" }));
      }} disabled={!form.sido}>
        <SelectTrigger><SelectValue placeholder="시군구 선택" /></SelectTrigger>
        <SelectContent>
          {selectedSido?.sigungu.map(s => (
            <SelectItem key={s.name} value={s.name}>{s.name}</SelectItem>
          ))}
        </SelectContent>
      </Select>

      <Select value={form.emd} onValueChange={(value) => {
        setForm(prev => ({ ...prev, emd: value }));
      }} disabled={!form.sgk}>
        <SelectTrigger><SelectValue placeholder="읍면동 선택" /></SelectTrigger>
        <SelectContent>
          {selectedSigungu?.eupmyeon.map(e => (
            <SelectItem key={e} value={e}>{e}</SelectItem>
          ))}
        </SelectContent>
      </Select>
    </div>
  );
};

export default RegionSelect;