package by.andersen.chronology.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import by.andersen.chronology.dto.TripDto;
import by.andersen.chronology.mapper.TripMapper;
import by.andersen.chronology.repository.TripRepository;
import by.andersen.chronology.service.TripService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;

    private final TripMapper tripMapper;

    private final UserDetails currentUser;

    @Override
    public List<TripDto> getAllTrips() {
        return tripRepository.findAllByOwner(currentUser.getUsername())
                .stream()
                .map(tripMapper::toTripDto)
                .toList();
    }

    @Override
    public TripDto getTripById(UUID id) {
        checkTripRelatesToUser(id);

        return tripRepository.findById(id)
                .map(entity -> tripMapper.toTripDto(entity))
                .orElseThrow();
    }

    @Override
    public TripDto createTrip(TripDto tripDto) {
        var tripEntity = tripMapper.toTripEntity(tripDto);
        tripRepository.save(tripEntity);
        return tripDto;
    }

    @Override
    public TripDto updateTrip(UUID id, TripDto tripDto) {
        checkTripRelatesToUser(id);
        var tripEntity = tripMapper.toTripEntity(tripDto);
        tripEntity.setId(id);
        tripRepository.save(tripEntity);
        return tripDto;
    }

    @Override
    public void deleteTripById(UUID id) {
        checkTripRelatesToUser(id);
        tripRepository.deleteById(id);
    }

    private void checkTripRelatesToUser(UUID id) {
        var isTripRelatesToUser = tripRepository.existsByIdAndOwner(id, currentUser.getUsername());
        if (!isTripRelatesToUser) {
            throw new IllegalArgumentException("Trip with id: " + id + " does not exist");
        }
    }

}
