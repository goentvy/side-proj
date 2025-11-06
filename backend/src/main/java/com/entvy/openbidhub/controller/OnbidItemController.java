package com.entvy.openbidhub.controller;

import com.entvy.openbidhub.dto.OnbidItemDto;
import com.entvy.openbidhub.service.OnbidItemSaveService;
import com.entvy.openbidhub.util.XmlParser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/onbid")
@RequiredArgsConstructor
public class OnbidItemController {

    private final OnbidItemSaveService saveService;

    @PostMapping("/sync")
    public String syncOnbidItems(@RequestBody String xml) {
        try {
            List<OnbidItemDto> dtos = XmlParser.parse(xml);
            saveService.saveAll(dtos);
            return "저장 완료: " + dtos.size() + "건";
        } catch (Exception e) {
            return "오류 발생: " + e.getMessage();
        }
    }
}