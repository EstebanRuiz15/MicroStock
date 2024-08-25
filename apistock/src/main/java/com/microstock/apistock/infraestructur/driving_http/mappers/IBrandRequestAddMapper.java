package com.microstock.apistock.infraestructur.driving_http.mappers;

import org.mapstruct.Mapper;

import com.microstock.apistock.domain.model.Brand;
import com.microstock.apistock.infraestructur.driving_http.dtos.request.BrandDtoAdd;

@Mapper(componentModel = "spring")
public interface IBrandRequestAddMapper {
    Brand toBrand(BrandDtoAdd brandAdd);
}
