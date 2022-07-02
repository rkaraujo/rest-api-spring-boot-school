package school.dto;

import school.model.Course;

import javax.validation.constraints.NotBlank;

public record CourseDTO(
        Integer id,
        @NotBlank String name,
        @NotBlank String code) {

    public static CourseDTO toDTO(Course course) {
        return new CourseDTO(course.getId(), course.getName(), course.getCode());
    }

    public Course toEntity() {
        Course course = new Course();
        course.setName(name());
        course.setCode(code());
        return course;
    }
}
