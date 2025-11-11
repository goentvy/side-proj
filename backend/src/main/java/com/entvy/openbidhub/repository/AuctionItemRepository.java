package com.entvy.openbidhub.repository;

import com.entvy.openbidhub.dto.AuctionCardDto;
import com.entvy.openbidhub.dto.OnbidItemSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuctionItemRepository {
    Page<AuctionCardDto> searchByCondition(OnbidItemSearchCondition cond, Pageable pageable);
}
