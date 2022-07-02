package school.dto;

import javax.validation.constraints.NotBlank;

public record StudentDTO(
        Integer id,
        @NotBlank String name) {
}
