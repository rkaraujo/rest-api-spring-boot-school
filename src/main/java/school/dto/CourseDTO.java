package school.dto;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public record CourseDTO(
        Integer id,
        @NotBlank String name,
        @NotBlank String code) {
}
