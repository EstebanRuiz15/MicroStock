package com.microstock.apistock.infraestructur.driven_rp.adapter;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.microstock.apistock.domain.interfaces.IBrandRepositoryPort;
import com.microstock.apistock.domain.model.Brand;
import com.microstock.apistock.infraestructur.driven_rp.mapper.IBrandToEntityMapper;
import com.microstock.apistock.infraestructur.driven_rp.persistence.BrandRepositoryJpa;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BrandRepositoryImpl implements IBrandRepositoryPort {
    private final BrandRepositoryJpa jparepository;
    private final IBrandToEntityMapper brandToEntityMapper;


    @Override
    public void saveBrand(Brand brand) {
        jparepository.save(brandToEntityMapper.toBrandEntity(brand));
    }

    @Override
    public Optional<Brand> findByNombreIgnoreCase(String name) {
        return jparepository.findByNameIgnoreCase(name);
    }
    
}
