package com.kosign.demo_jpa.mapper;

import com.kosign.demo_jpa.dto.ModelDTO;
import com.kosign.demo_jpa.entity.Brand;
import com.kosign.demo_jpa.entity.Model;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ModelMapper {
    ModelMapper INSTANCE = Mappers.getMapper(ModelMapper.class);

    @Mapping(target = "brand", source = "brandId")
    Model toProduct(ModelDTO modelDTO);

    default Brand toCategory(Integer modelId) {
        Brand brand = new Brand();
        brand.setId(modelId);
        return brand;
    }
}
