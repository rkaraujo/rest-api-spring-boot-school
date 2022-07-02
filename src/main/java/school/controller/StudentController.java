package school.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import school.dto.CourseDTO;
import school.dto.StudentDTO;
import school.model.Course;
import school.model.Student;
import school.service.StudentService;

import javax.validation.Valid;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/v1/student")
    public StudentDTO create(@Valid @RequestBody StudentDTO studentDTO) {
        Student createdStudent = studentService.create(toEntity(studentDTO));
        return toDTO(createdStudent);
    }

    private StudentDTO toDTO(Student student) {
        return new StudentDTO(student.getId(), student.getName());
    }

    private Student toEntity(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.name());
        return student;
    }

}
