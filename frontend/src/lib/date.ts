// 사용자가 20250901140000 입력 → "25.09.01 오후 02시"로 변환
export function formatToFullDate(raw: string): string {
  if (!raw || raw.length !== 14) return raw;
  const yy = raw.slice(2, 4);
  const MM = raw.slice(4, 6);
  const dd = raw.slice(6, 8);
  const HH = parseInt(raw.slice(8, 10), 10);
  const period = HH < 12 ? "오전" : "오후";
  const hour12 = HH % 12 === 0 ? 12 : HH % 12;
  return `${yy}.${MM}.${dd} ${period} ${hour12.toString().padStart(2, "0")}시`;
}

// 관리번호 -> 2025-09964-001 형식으로 변환
export function formatCltrMnmtNo(raw: string): string {
  if (!raw || raw.length !== 12) return raw;
  return `${raw.slice(0, 4)}-${raw.slice(4, 9)}-${raw.slice(9)}`;
}
