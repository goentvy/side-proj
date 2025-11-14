import { useEffect, useMemo } from "react"
import { TrendingUp } from "lucide-react"
import { Bar, BarChart, CartesianGrid, Cell, LabelList, XAxis, YAxis } from "recharts"
import { useQuery } from "@tanstack/react-query"
import axios from "@/lib/axios"
import toast from "react-hot-toast"

import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card"

import {
  type ChartConfig,
  ChartContainer,
  ChartTooltip,
  ChartTooltipContent,
} from "@/components/ui/chart"


export const description = "지역별 입찰 수량 막대 그래프"

type RegionBidSummary = {
  region: string
  count: number
}

const chartConfig: ChartConfig = {
  count: {
    label: "입찰 수량",
    color: "var(--chart-2)",
  },
  label: {
    color: "var(--background)",
  },
}

const regionColors = [
  "#FF4C4C", 
  "#FF8040", 
  "#FFA500", 
  "#FFD700", 
  "#FFFF66", 
  "#CCFF66", 
  "#99FF99", 
  "#66CC66", 
  "#66CCCC", 
  "#66B2FF", 
  "#3399FF", 
  "#3366CC", 
  "#6666FF", 
  "#9966CC", 
  "#CC66CC", 
  "#FF66B2"
]

export function RegionBidChart() {

  const {
    data,
    isLoading,
    error,
  } = useQuery<RegionBidSummary[]>({
    queryKey: ["regionBidSummary"],
    queryFn: async () => {
      const res = await axios.get("/api/auction-items/region-summary")
      return res.data
    },
  })

  useEffect(() => {
    if (error) {
      console.error("지역별 입찰 수량 API 에러:", error)
      toast.error("지역별 입찰 수량 데이터를 불러오는 중 문제가 발생했습니다.")
    }
  }, [error])

  const chartData = useMemo(() => {
    return data?.map(({ region, count }) => ({ region, count })) ?? []
  }, [data])

  if (isLoading) {
    return (
      <Card>
        <CardHeader>
          <CardTitle>지역별 입찰 수량</CardTitle>
          <CardDescription>로딩 중입니다...</CardDescription>
        </CardHeader>
        <CardContent className="h-40 flex items-center justify-center text-muted-foreground">
          데이터 불러오는 중...
        </CardContent>
      </Card>
    )
  }

  return (
    <Card className="mt-3">
      <CardHeader>
        <CardTitle>지역별 입찰 수량</CardTitle>
        <CardDescription>현재 등록된 입찰 대상 기준</CardDescription>
      </CardHeader>
      <CardContent>
        <ChartContainer config={chartConfig}>
          <BarChart
            accessibilityLayer
            data={chartData}
            layout="vertical"
            margin={{ right: 16 }}
          >
            <CartesianGrid horizontal={false} />
            <YAxis
              dataKey="region"
              type="category"
              tickLine={false}
              tickMargin={10}
              axisLine={false}
            />
            <XAxis dataKey="count" type="number" hide />
            <ChartTooltip
              cursor={false}
              content={<ChartTooltipContent indicator="line" />}
            />
            <Bar dataKey="count" layout="vertical" radius={4}>
              {chartData.map((entry, index) => (
                <Cell key={`cell-${index}`} fill={regionColors[index % regionColors.length]} />
              ))}
              <LabelList
                dataKey="region"
                position="insideLeft"
                offset={8}
                className="fill-(--color-label)"
                fontSize={12}
              />
              <LabelList
                dataKey="count"
                position="right"
                offset={8}
                className="fill-foreground"
                fontSize={12}
              />
            </Bar>
          </BarChart>
        </ChartContainer>
      </CardContent>
      <CardFooter className="flex-col items-start gap-2 text-sm">
        <div className="flex gap-2 leading-none font-medium">
          지역별 입찰 집중도 분석 <TrendingUp className="h-4 w-4" />
        </div>
        <div className="text-muted-foreground leading-none">
          입찰 대상이 많은 순서대로 정렬됨
        </div>
      </CardFooter>
    </Card>
  )
}