package by.andersen.chronology.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import by.andersen.chronology.dto.TripDto;
import by.andersen.chronology.service.TripService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TripController {

    /**
     * Controller for handling GraphQL queries and mutations related to trips.
     */
    private final TripService tripService;

    /**
     * Retrieves all trips.
     *
     * @return A list of {@link TripDto} objects representing trips.
     */
    @QueryMapping
    public List<TripDto> getAllTrips() {
        log.debug("Get all trips");
        return tripService.getAllTrips();
    }

    /**
     * Retrieves a trip by its ID.
     *
     * @param id The ID of the trip.
     * @return A {@link TripDto} object representing the trip.
     */
    @QueryMapping
    public TripDto getTripById(@Argument UUID id) {
        log.debug("Get trip by id: {}", id);
        return tripService.getTripById(id);
    }

    /**
     * Creates a new trip with the provided trip data.
     *
     * @param trip The {@link TripDto} object representing the trip data.
     * @return The newly created {@link TripDto} object.
     */
    @MutationMapping
    public TripDto createTrip(@Validated @Argument TripDto trip) {
        log.debug("Create trip: {}", trip);
        return tripService.createTrip(trip);
    }

    /**
     * Updates an existing trip with the provided trip data.
     *
     * @param id     The ID of the trip to be updated.
     * @param trip   The {@link TripDto} object representing the updated trip data.
     * @return The updated {@link TripDto} object.
     */
    @MutationMapping
    public TripDto updateTrip(@Argument UUID id, @Validated @Argument TripDto trip) {
        log.debug("Update trip with id: {}, new data: {}", id, trip);
        return tripService.updateTrip(id, trip);
    }

    /**
     * Deletes a trip by its ID.
     *
     * @param id The ID of the trip to be deleted.
     */
    @MutationMapping
    public String deleteTripById(@Argument UUID id) {
        log.debug("Delete trip by id: {}", id);
        tripService.deleteTripById(id);
        return "Trip has been deleted successfully";
    }

}
