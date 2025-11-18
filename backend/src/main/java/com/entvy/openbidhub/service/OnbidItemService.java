package com.entvy.openbidhub.service;

import com.entvy.openbidhub.dto.ItemDetailDto;
import com.entvy.openbidhub.mapper.OnbidItemMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OnbidItemService {

    private final OnbidItemMapper itemMapper;

    public ItemDetailDto getItemDetail(String cltrMnmtNo) {
        ItemDetailDto dto = itemMapper.selectItemDetail(cltrMnmtNo);
        if (dto == null) {
            throw new EntityNotFoundException("해당 물건을 찾을 수 없습니다.");
        }

        if (dto.getCltrImgFiles() != null) {
            List<String> images = Arrays.stream(dto.getCltrImgFiles().split(","))
                    .map(String::trim)
                    .filter(s -> !s.isBlank())
                    .toList();
            dto.setCltrImgFileList(images);
        } else {
            dto.setCltrImgFileList(List.of());
        }

        return dto;
    }
}