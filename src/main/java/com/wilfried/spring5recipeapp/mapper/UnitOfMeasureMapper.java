package com.wilfried.spring5recipeapp.mapper;

import com.wilfried.spring5recipeapp.domain.UnitOfMeasure;
import com.wilfried.spring5recipeapp.dto.UnitOfMeasureDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UnitOfMeasureMapper {

    UnitOfMeasureMapper INSTANCE = Mappers.getMapper(UnitOfMeasureMapper.class);
    UnitOfMeasureDto unitOfMeasureToUnitOfMeasureDto(UnitOfMeasure unitOfMeasure);
}
