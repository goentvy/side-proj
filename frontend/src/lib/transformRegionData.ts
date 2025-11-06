type Town = {
  adm_nm: string;
  adm_cd: string;
};

type District = {
  adm_nm: string;
  adm_cd: string;
  towns: Town[];
};

type NestedDistrict = {
  adm_nm: string;
  adm_cd: string;
  districts: District[];
  towns?: undefined;
};

type RegionEntry = {
  timestamp: string;
  adm_nm: string;
  adm_cd: string;
  districts: (District | NestedDistrict)[];
};

export type RawRegionData = {
  timestamp: string;
  regions: RegionEntry[];
};

type Region = {
  sido: string;
  sigungu: {
    name: string;
    eupmyeon: string[];
  }[];
};

export function transformRegionData(raw: RawRegionData): Region[] {
  return raw.regions.map((sido) => ({
    sido: sido.adm_nm,
    sigungu: sido.districts.flatMap((sgk) => {
      if ("towns" in sgk && Array.isArray(sgk.towns)) {
        return [{
          name: sgk.adm_nm,
          eupmyeon: sgk.towns.map((town) => town.adm_nm),
        }];
      } else if ("districts" in sgk && Array.isArray(sgk.districts)) {
        return sgk.districts.map((emd) => ({
          name: emd.adm_nm,
          eupmyeon: emd.towns.map((town) => town.adm_nm),
        }));
      }
      return [];
    }),
  }));
}