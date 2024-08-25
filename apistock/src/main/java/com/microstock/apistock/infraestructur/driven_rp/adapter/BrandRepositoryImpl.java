package com.microstock.apistock.infraestructur.driven_rp.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.microstock.apistock.domain.interfaces.IBrandRepositoryPort;
import com.microstock.apistock.domain.model.Brand;
import com.microstock.apistock.infraestructur.driven_rp.entity.BrandEntity;
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

    @Override
    public List<Brand> findByBrand(String orden) {
        Sort.Direction direction = Sort.Direction.fromString(orden.toUpperCase());
        Sort sort = Sort.by(direction,"name");
        List<BrandEntity> categoriaEntities=jparepository.findAll(sort);
            
        return categoriaEntities.stream()
                .map(brandToEntityMapper::toBrand)
                .collect(Collectors.toList());
    }
    
}
