import type { OnbidItemSearchCondition } from "@/types";
import { ConditionLabels } from "./ConditionLabels";

export const RenderConditionSummary = (cond: Record<string, string>) => {
  if (Object.keys(cond).length === 0) return null;

  return (
    <div className="mb-4 text-sm text-gray-500 space-y-1">
      <div className="font-medium">검색 조건 요약:</div>
      <ul className="list-disc list-inside">
        {Object.entries(cond).map(([key, value]) => {
            const label = ConditionLabels[key as keyof OnbidItemSearchCondition] ?? key;
            return <li key={key}>{label}: {value}</li>;
        })}
      </ul>
    </div>
  );
};