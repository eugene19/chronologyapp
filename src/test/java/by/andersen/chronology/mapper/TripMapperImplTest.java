package by.andersen.chronology.mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import by.andersen.chronology.dto.CoordinateDto;
import by.andersen.chronology.dto.TripDto;
import by.andersen.chronology.entity.CoordinateEntity;
import by.andersen.chronology.entity.TripEntity;
import by.andersen.chronology.mapper.impl.TripMapperImpl;

@ExtendWith(SpringExtension.class)
public class TripMapperImplTest {

    @Mock
    private CoordinateMapper coordinateMapper;

    @Mock
    private UserDetails currentUser;

    @InjectMocks
    private TripMapperImpl tripMapper;

    @Test
    void toTripDtoTest() {
        Mockito.when(coordinateMapper.toCoordinateDtoList(Mockito.anyList())).thenReturn(List.of(CoordinateDto.builder().latitude(1.0).longitude(2.0).build()));
        TripEntity tripEntity = TripEntity.builder()
                .id(UUID.randomUUID())
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(1))
                .coordinates(List.of(CoordinateEntity.builder().latitude(1.0).longitude(2.0).build()))
                .owner("user")
                .guests(List.of("guest1", "guest2"))
                .build();
        TripDto tripDto = tripMapper.toTripDto(tripEntity);
        Assertions.assertEquals(tripEntity.getId(), tripDto.id());
        Assertions.assertEquals(tripEntity.getStartDate(), tripDto.startDate());
        Assertions.assertEquals(tripEntity.getEndDate(), tripDto.endDate());
        Assertions.assertEquals(tripEntity.getCoordinates().size(), tripDto.coordinates().size());
        Assertions.assertEquals(tripEntity.getOwner(), tripDto.owner());
        Assertions.assertEquals(tripEntity.getGuests().size(), tripDto.guests().size());
    }
    
    @Test
    void toTripEntityTest() {
        Mockito.when(coordinateMapper.toCoordinateDtoList(Mockito.anyList())).thenReturn(List.of(CoordinateDto.builder().latitude(1.0).longitude(2.0).build()));
        Mockito.when(currentUser.getUsername()).thenReturn("user");
        TripDto tripDto = TripDto.builder()
                .id(UUID.randomUUID())
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(1))
                .coordinates(List.of(CoordinateDto.builder().latitude(1.0).longitude(2.0).build()))
                .owner("user")
                .guests(List.of("guest1", "guest2"))
                .build();
        TripEntity tripEntity = tripMapper.toTripEntity(tripDto);
        Assertions.assertEquals(tripDto.id(), tripEntity.getId());
        Assertions.assertEquals(tripDto.startDate(), tripEntity.getStartDate());
        Assertions.assertEquals(tripDto.endDate(), tripEntity.getEndDate());
        Assertions.assertEquals(tripDto.owner(), tripEntity.getOwner());
        Assertions.assertEquals(tripDto.guests().size(), tripEntity.getGuests().size());
    }
    
    @Test
    void toTripDtoListTest() {
        Mockito.when(coordinateMapper.toCoordinateDtoList(Mockito.anyList())).thenReturn(List.of(CoordinateDto.builder().latitude(1.0).longitude(2.0).build()));
        List<TripEntity> tripEntities = List.of(
                TripEntity.builder().id(UUID.randomUUID()).build(),
                TripEntity.builder().id(UUID.randomUUID()).build());
        List<TripDto> tripDtos = tripMapper.toTripDtoList(tripEntities);
        Assertions.assertEquals(2, tripDtos.size());
    }
    
    @Test
    void toTripEntityListTest() {
        Mockito.when(coordinateMapper.toCoordinateDtoList(Mockito.anyList())).thenReturn(List.of(CoordinateDto.builder().latitude(1.0).longitude(2.0).build()));
        Mockito.when(currentUser.getUsername()).thenReturn("user");
        List<TripDto> tripDtos = List.of(
                TripDto.builder().id(UUID.randomUUID()).build(),
                TripDto.builder().id(UUID.randomUUID()).build());
        List<TripEntity> tripEntities = tripMapper.toTripEntityList(tripDtos);
        Assertions.assertEquals(2, tripEntities.size());
    }

}
