package by.andersen.chronology.mapper;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import by.andersen.chronology.dto.CoordinateDto;
import by.andersen.chronology.entity.CoordinateEntity;
import by.andersen.chronology.mapper.impl.CoordinateMapperImpl;

@ExtendWith(SpringExtension.class)
public class CoordinateMapperImplTest {

    @InjectMocks
    private CoordinateMapperImpl coordinateMapper;

    @Test
    void toCoordinateDtoTest() {
        CoordinateEntity coordinateEntity = CoordinateEntity.builder()
                .latitude(1.0)
                .longitude(2.0)
                .build();
        CoordinateDto coordinateDto = coordinateMapper.toCoordinateDto(coordinateEntity);
        Assertions.assertEquals(1.0, coordinateDto.latitude());
        Assertions.assertEquals(2.0, coordinateDto.longitude());
    }

    @Test
    void toCoordinateEntityTest() {
        CoordinateDto coordinateDto = CoordinateDto.builder()
                .latitude(1.0)
                .longitude(2.0)
                .build();
        CoordinateEntity coordinateEntity = coordinateMapper.toCoordinateEntity(coordinateDto);
        Assertions.assertEquals(1.0, coordinateEntity.getLatitude());
        Assertions.assertEquals(2.0, coordinateEntity.getLongitude());
    }

    @Test
    void toCoordinateDtoListTest() {
        List<CoordinateEntity> coordinateEntities = List.of(
                CoordinateEntity.builder().latitude(1.0).longitude(2.0).build(),
                CoordinateEntity.builder().latitude(3.0).longitude(4.0).build());
        List<CoordinateDto> coordinateDtos = coordinateMapper.toCoordinateDtoList(coordinateEntities);
        Assertions.assertEquals(2, coordinateDtos.size());
        Assertions.assertEquals(1.0, coordinateDtos.get(0).latitude());
        Assertions.assertEquals(2.0, coordinateDtos.get(0).longitude());
        Assertions.assertEquals(3.0, coordinateDtos.get(1).latitude());
        Assertions.assertEquals(4.0, coordinateDtos.get(1).longitude());
    }

    @Test
    void toCoordinateEntityListTest() {
        List<CoordinateDto> coordinateDtos = List.of(
                CoordinateDto.builder().latitude(1.0).longitude(2.0).build(),
                CoordinateDto.builder().latitude(3.0).longitude(4.0).build());
        List<CoordinateEntity> coordinateEntities = coordinateMapper.toCoordinateEntityList(coordinateDtos);
        Assertions.assertEquals(2, coordinateEntities.size());
        Assertions.assertEquals(1.0, coordinateEntities.get(0).getLatitude());
        Assertions.assertEquals(2.0, coordinateEntities.get(0).getLongitude());
        Assertions.assertEquals(3.0, coordinateEntities.get(1).getLatitude());
        Assertions.assertEquals(4.0, coordinateEntities.get(1).getLongitude());
    }

}
