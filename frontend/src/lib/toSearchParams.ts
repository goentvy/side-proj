export function toSearchParams(obj: Record<string, any>) {
  const filtered: Record<string, string> = {};
  for (const [key, value] of Object.entries(obj)) {
    if (value !== undefined && value !== null) {
      filtered[key] = String(value);
    }
  }
  return new URLSearchParams(filtered);
}