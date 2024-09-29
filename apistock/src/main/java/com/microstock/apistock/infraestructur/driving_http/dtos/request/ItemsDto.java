package com.microstock.apistock.infraestructur.driving_http.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ItemsDto {
    private Integer productId;
    private Integer quantity;
}
