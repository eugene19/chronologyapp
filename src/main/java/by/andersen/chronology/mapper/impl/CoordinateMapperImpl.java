package by.andersen.chronology.mapper.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import by.andersen.chronology.dto.CoordinateDto;
import by.andersen.chronology.entity.CoordinateEntity;
import by.andersen.chronology.mapper.CoordinateMapper;

@Component
public class CoordinateMapperImpl implements CoordinateMapper {

    @Override
    public CoordinateDto toCoordinateDto(CoordinateEntity entity) {
        return CoordinateDto.builder()
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .build();
    }

    @Override
    public CoordinateEntity toCoordinateEntity(CoordinateDto dto) {
        return CoordinateEntity.builder()
        .latitude(dto.latitude())
        .longitude(dto.longitude())
        .build();
    }

    @Override
    public List<CoordinateDto> toCoordinateDtoList(List<CoordinateEntity> entities) {
        return entities.stream()
                .map(this::toCoordinateDto)
                .toList();
    }

    @Override
    public List<CoordinateEntity> toCoordinateEntityList(List<CoordinateDto> dtos) {
        return dtos.stream()
                .map(this::toCoordinateEntity)
                .toList();
    }
}
