package by.andersen.chronology.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import by.andersen.chronology.dto.TripDto;
import by.andersen.chronology.entity.TripEntity;
import by.andersen.chronology.mapper.TripMapper;
import by.andersen.chronology.repository.TripRepository;

@ExtendWith(SpringExtension.class)
public class TripServiceImplTest {

    @Mock
    private TripRepository tripRepository;

    @Mock
    private TripMapper tripMapper;

    @Mock
    private UserDetails currentUser;

    private TripServiceImpl tripService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tripService = new TripServiceImpl(tripRepository, tripMapper, currentUser);
    }

    @Test
    void testGetAllTripsReturnsNonNullList() {
        when(tripRepository.findAllByOwner(any())).thenReturn(List.of(new TripEntity()));
        List<TripDto> trips = tripService.getAllTrips();
        assertNotNull(trips);
    }

    @Test
    void testGetAllTripsReturnsCorrectNumberOfTripDtoObjects() {
        when(tripRepository.findAllByOwner(any())).thenReturn(List.of(new TripEntity(), new TripEntity()));
        when(tripMapper.toTripDto(any())).thenReturn(TripDto.builder().build());
        List<TripDto> trips = tripService.getAllTrips();
        assertEquals(2, trips.size());
    }
    
}
