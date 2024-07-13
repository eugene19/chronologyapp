package by.andersen.chronology.mapper;

import java.util.List;

import by.andersen.chronology.dto.CoordinateDto;
import by.andersen.chronology.entity.CoordinateEntity;

/**
 * {@link CoordinateMapper} provides conversion between {@link CoordinateEntity} and {@link CoordinateDto}.
 */
public interface CoordinateMapper {

    /**
     * Converts {@link CoordinateEntity} to {@link CoordinateDto}.
     *
     * @param entity the {@link CoordinateEntity} to convert
     * @return the converted {@link CoordinateDto}
     */
    CoordinateDto toCoordinateDto(CoordinateEntity entity);

    /**
     * Converts a list of {@link CoordinateEntity} to a list of {@link CoordinateDto}.
     *
     * @param entities the list of {@link CoordinateEntity} to convert
     * @return the converted list of {@link CoordinateDto}
     */
    List<CoordinateDto> toCoordinateDtoList(List<CoordinateEntity> entities);

    /**
     * Converts {@link CoordinateDto} to {@link CoordinateEntity}.
     *
     * @param dto the {@link CoordinateDto} to convert
     * @return the converted {@link CoordinateEntity}
     */
    CoordinateEntity toCoordinateEntity(CoordinateDto dto);

    /**
     * Converts a list of {@link CoordinateDto} to a list of {@link CoordinateEntity}.
     *
     * @param dtos the list of {@link CoordinateDto} to convert
     * @return the converted list of {@link CoordinateEntity}
     */
    List<CoordinateEntity> toCoordinateEntityList(List<CoordinateDto> dtos);
}
