package school.dto;

import school.model.Student;

import javax.validation.constraints.NotBlank;

public record StudentDTO(
        Integer id,
        @NotBlank String name) {

    public static StudentDTO toDTO(Student student) {
        return new StudentDTO(student.getId(), student.getName());
    }

    public Student toEntity() {
        Student student = new Student();
        student.setName(name());
        return student;
    }
}
