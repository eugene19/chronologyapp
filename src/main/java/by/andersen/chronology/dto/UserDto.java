package by.andersen.chronology.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record UserDto(
        @NotNull @Pattern(regexp = "^[a-zA-Z0-9]{5,20}$") String username,
        @NotNull @Pattern(regexp = "^[a-zA-Z0-9]{5,20}$") String password) {
}
