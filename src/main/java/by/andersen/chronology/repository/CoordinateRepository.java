package by.andersen.chronology.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import by.andersen.chronology.entity.CoordinateEntity;

@Repository
public interface CoordinateRepository extends JpaRepository<CoordinateEntity, UUID> {
}
