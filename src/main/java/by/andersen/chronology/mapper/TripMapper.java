package by.andersen.chronology.mapper;

import java.util.List;

import by.andersen.chronology.dto.TripDto;
import by.andersen.chronology.entity.TripEntity;

/**
 * Mapper for {@link TripEntity} and {@link TripDto}.
 */
public interface TripMapper {

    /**
     * Convert {@link TripEntity} to {@link TripDto}.
     *
     * @param tripEntity the trip entity to convert
     * @return the resulting trip DTO
     */
    TripDto toTripDto(TripEntity tripEntity);
    
    /**
     * Convert list of {@link TripEntity} to list of {@link TripDto}.
     *
     * @param tripEntities the list of trip entities to convert
     * @return the resulting list of trip DTOs
     */
    List<TripDto> toTripDtoList(List<TripEntity> tripEntities);
    
    /**
     * Convert {@link TripDto} to {@link TripEntity}.
     *
     * @param tripDto the trip DTO to convert
     * @return the resulting trip entity
     */
    TripEntity toTripEntity(TripDto tripDto);
    
    /**
     * Convert list of {@link TripDto} to list of {@link TripEntity}.
     *
     * @param tripDtos the list of trip DTOs to convert
     * @return the resulting list of trip entities
     */
    List<TripEntity> toTripEntityList(List<TripDto> tripDtos);

}
