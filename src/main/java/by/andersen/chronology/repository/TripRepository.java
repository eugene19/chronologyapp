package by.andersen.chronology.repository;

import java.util.Collection;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import by.andersen.chronology.entity.TripEntity;

/**
 * Repository for managing {@link TripEntity} entities.
 */
@Repository
public interface TripRepository extends JpaRepository<TripEntity, UUID> {

    /**
     * Finds all trips for a given user.
     *
     * @param username username of the user
     * @return collection of trips belonging to the user
     */
    Collection<TripEntity> findAllByOwner(String username);

    /**
     * Checks if a trip with the given id belongs to a user with the given username.
     *
     * @param id id of the trip
     * @param owner username of the user
     * @return true if the trip belongs to the user, false otherwise
     */
    boolean existsByIdAndOwner(UUID id, String owner);

}
