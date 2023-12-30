package com.kosign.phone_shop.mapper;

import com.kosign.phone_shop.dto.BrandDTO;
import com.kosign.phone_shop.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BrandMapper {

//    add this if properties in entity and dto is different name
//    @Mapping(source = "vs", target = "version")

    BrandMapper INSTANT = Mappers.getMapper(BrandMapper.class);
    Brand toBrand(BrandDTO dto);

    BrandDTO toBrandDTO(Brand entity);
}
