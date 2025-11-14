export function QuickActions({ role }: { role?: string }) {
  const actions = {
    USER: ['입찰 등록', '문서 업로드'],
    ADMIN: ['사용자 관리', '입찰 승인'],
    PARTNER: ['제안 요청', '계약 확인'],
  };

  return (
    <div>
      <h2 className="text-lg font-semibold mb-2">빠른 액션</h2>
      <div className="flex gap-2">
        {actions[role as keyof typeof actions]?.map((action) => (
          <button key={action} className="px-4 py-2 bg-primary text-white rounded text-sm">
            {action}
          </button>
        ))}
      </div>
    </div>
  );
}