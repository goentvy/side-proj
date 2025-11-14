export function RecentNotifications() {
  const notifications = [
    '입찰 마감 2일 전: 서울시청 공고',
    '새로운 입찰 요청 도착',
    '계약서 검토 요청',
  ];

  return (
    <div>
      <h2 className="text-lg font-semibold mb-2">최근 알림</h2>
      <ul className="space-y-1 text-sm">
        {notifications.map((note, idx) => (
          <li key={idx} className="text-muted-foreground">• {note}</li>
        ))}
      </ul>
    </div>
  );
}