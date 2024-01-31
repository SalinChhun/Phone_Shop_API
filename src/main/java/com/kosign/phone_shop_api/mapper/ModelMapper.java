package com.kosign.phone_shop_api.mapper;

import com.kosign.phone_shop_api.dto.ModelDTO;
import com.kosign.phone_shop_api.entity.Brand;
import com.kosign.phone_shop_api.entity.Model;
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
