package by.andersen.chronology.mapper.impl;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import by.andersen.chronology.dto.TripDto;
import by.andersen.chronology.entity.TripEntity;
import by.andersen.chronology.mapper.CoordinateMapper;
import by.andersen.chronology.mapper.TripMapper;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TripMapperImpl implements TripMapper {

    private final CoordinateMapper coordinateMapper;

    private final UserDetails currentUser;

    @Override
    public TripDto toTripDto(TripEntity tripEntity) {
        var coordinatesDtos = coordinateMapper.toCoordinateDtoList(tripEntity.getCoordinates());
        return TripDto.builder()
                .id(tripEntity.getId())
                .startDate(tripEntity.getStartDate())
                .endDate(tripEntity.getEndDate())
                .owner(tripEntity.getOwner())
                .guests(tripEntity.getGuests())
                .coordinates(coordinatesDtos)
                .build();
    }

    @Override
    public List<TripDto> toTripDtoList(List<TripEntity> tripEntities) {
        return tripEntities.stream()
                .map(this::toTripDto)
                .toList();
    }

    @Override
    public TripEntity toTripEntity(TripDto tripDto) {
        var coordinatesEntities = coordinateMapper.toCoordinateEntityList(tripDto.coordinates());

        return TripEntity.builder()
                .id(tripDto.id())
                .startDate(tripDto.startDate())
                .endDate(tripDto.endDate())
                .coordinates(coordinatesEntities)
                .owner(currentUser.getUsername())
                .guests(tripDto.guests())
                .build();
    }

    @Override
    public List<TripEntity> toTripEntityList(List<TripDto> tripDtos) {
        return tripDtos.stream()
                .map(this::toTripEntity)
                .toList();
    }
}

