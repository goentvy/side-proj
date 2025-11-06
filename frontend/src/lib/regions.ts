import rawData from "@/data/regions.json";
import { transformRegionData } from "@/lib/transformRegionData";

export const regions = transformRegionData(rawData);