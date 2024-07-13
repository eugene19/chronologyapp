package by.andersen.chronology.service;

import java.util.List;
import java.util.UUID;

import by.andersen.chronology.dto.TripDto;

/**
 * Service interface for managing trips.
 */
public interface TripService {

    /**
     * Retrieves all trips.
     * 
     * @return A list of {@link TripDto} objects representing trips.
     */
    List<TripDto> getAllTrips();

    /**
     * Retrieves a trip by its ID.
     * 
     * @param id The ID of the trip.
     * @return A {@link TripDto} object representing the trip.
     */
    TripDto getTripById(UUID id);

    /**
     * Creates a new trip with the provided trip data.
     * 
     * @param tripDto The {@link TripDto} object representing the trip data.
     * @return The newly created {@link TripDto} object.
     */
    TripDto createTrip(TripDto tripDto);

    /**
     * Updates an existing trip with the provided trip data.
     * 
     * @param id     The ID of the trip to be updated.
     * @param tripDto The {@link TripDto} object representing the updated trip data.
     * @return The updated {@link TripDto} object.
     */
    TripDto updateTrip(UUID id, TripDto tripDto);

    /**
     * Deletes a trip by its ID.
     * 
     * @param id The ID of the trip to be deleted.
     */
    void deleteTripById(UUID id);

}
