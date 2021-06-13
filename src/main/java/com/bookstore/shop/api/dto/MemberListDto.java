package com.bookstore.shop.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberListDto {
    private Long id;
    private String name;
}
