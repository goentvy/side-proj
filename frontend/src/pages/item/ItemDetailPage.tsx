import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { Button } from '@/components/ui';
import api from '@/lib/api';
import type { ItemDetail } from '@/types';

export default function ItemDetailPage() {
  const { cltrMnmtNo } = useParams();
  const [item, setItem] = useState<ItemDetail | null>(null);

  useEffect(() => {
    api.get(`/items/${cltrMnmtNo}`)
      .then(res => setItem(res.data))
      .catch(() => console.error("물건 정보를 불러올 수 없습니다."));
  }, [cltrMnmtNo]);

  if (!item) return <div className="p-8 text-center">로딩 중...</div>;

  return (
    <div className="max-w-4xl mx-auto p-6 space-y-6">
      <h1 className="text-2xl font-bold">{item.cltrNm}</h1>
      <p className="text-gray-600">{item.ldnmAdrs || item.nmrdAdrs}</p>

      <div className="grid grid-cols-2 gap-4">
        <div>
          <p><strong>감정가:</strong> {item.apslAsesAvgAmt.toLocaleString()}원</p>
          <p><strong>최저입찰가:</strong> {item.minBidPrc.toLocaleString()}원</p>
          <p><strong>입찰일시:</strong> {item.pbctBegnDtm}</p>
          <p><strong>입찰상태:</strong> {item.pbctCltrStatNm}</p>
        </div>
        <div>
          <p><strong>사건번호:</strong> {item.pbctNo}</p>
          <p><strong>법원:</strong> {item.scrtNm}</p>
          <p><strong>분류:</strong> {item.ctgrFullNm}</p>
          <p><strong>조회수:</strong> {item.iqryCnt}</p>
        </div>
      </div>

      <div>
        <h2 className="text-lg font-semibold mt-4">물건 설명</h2>
        <p>{item.goodsNm || '설명 없음'}</p>
      </div>

      <div className="grid grid-cols-3 gap-4 mt-6">
        {item.cltrImgFileList.length > 0 ? (
          item.cltrImgFileList.map((url, idx) => (
            <img key={idx} src={url} alt={`물건 이미지 ${idx + 1}`} className="rounded shadow" />
          ))
        ) : (
          <p className="col-span-3 text-gray-500">이미지가 없습니다.</p>
        )}
      </div>

      <div className="mt-6">
        <Button className="w-full">입찰 참여하기</Button>
      </div>
    </div>
  );
}