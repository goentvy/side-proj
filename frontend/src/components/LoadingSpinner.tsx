import { Loader2 } from "lucide-react";
import { cn } from "@/lib/utils";

interface Props {
  size?: number;
  className?: string;
}

const LoadingSpinner = ({ size = 24, className }: Props) => {
  return (
    <div className={cn("flex items-center justify-center py-4", className)}>
      <Loader2 className="animate-spin text-muted-foreground" size={size} />
    </div>
  );
};

export default LoadingSpinner;