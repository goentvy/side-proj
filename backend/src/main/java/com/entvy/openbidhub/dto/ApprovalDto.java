package com.entvy.openbidhub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApprovalDto {
    private Long itemId;
    private String itemName;
    private String status;
    private String createdAt;
}