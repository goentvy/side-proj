package com.entvy.openbidhub.service;

import com.entvy.openbidhub.domain.OnbidItemEntity;
import com.entvy.openbidhub.dto.OnbidItemDto;
import com.entvy.openbidhub.repository.OnbidItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OnbidItemSaveService {

    private final OnbidItemRepository repository;

    public void saveAll(List<OnbidItemDto> dtos) {
        List<OnbidItemEntity> entities = dtos.stream()
                .map(OnbidItemEntity::from)
                .toList();
        repository.saveAll(entities);
    }
}