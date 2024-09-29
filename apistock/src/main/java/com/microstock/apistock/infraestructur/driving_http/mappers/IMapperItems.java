package com.microstock.apistock.infraestructur.driving_http.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.microstock.apistock.domain.model.Item;
import com.microstock.apistock.infraestructur.driving_http.dtos.request.ItemsDto;

@Mapper(componentModel = "spring")
public interface IMapperItems {
    Item toItem(ItemsDto items);
    List<Item> toItemList(List<ItemsDto> items);
}
