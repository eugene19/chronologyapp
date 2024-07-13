package by.andersen.chronology.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CoordinateDto(
    @NotNull
    Double latitude,
    @NotNull
    Double longitude
) {

}
