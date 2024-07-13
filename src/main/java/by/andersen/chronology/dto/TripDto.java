package by.andersen.chronology.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record TripDto(
    UUID id,
    @NotNull
    LocalDate startDate,
    @NotNull
    LocalDate endDate,
    @Size(min = 2)
    List<CoordinateDto> coordinates,
    String owner,
    List<String> guests) {

}
