package com.microstock.apistock.infraestructur.driven_rp.mapper;

import org.mapstruct.Mapper;

import com.microstock.apistock.domain.model.Brand;
import com.microstock.apistock.infraestructur.driven_rp.entity.BrandEntity;

@Mapper(componentModel = "spring")

public interface IBrandToEntityMapper {
    BrandEntity toBrandEntity(Brand brand);
    Brand toBrand (BrandEntity brandEntity);
    
}
