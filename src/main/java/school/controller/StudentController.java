package school.controller;

import org.springframework.web.bind.annotation.*;
import school.dto.CourseDTO;
import school.dto.StudentDTO;
import school.model.Course;
import school.model.Student;
import school.service.StudentService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/v1/student")
    public StudentDTO create(@Valid @RequestBody StudentDTO studentDTO) {
        Student createdStudent = studentService.create(studentDTO.toEntity());
        return StudentDTO.toDTO(createdStudent);
    }

    @PutMapping("/v1/student/{studentId}/register-to-course/{courseId}")
    public void registerToCourse(@PathVariable Integer studentId, @PathVariable Integer courseId) {
        studentService.registerToCourse(studentId, courseId);
    }

    @GetMapping("/v1/student/{id}/courses")
    public List<CourseDTO> getCourses(@PathVariable Integer id) {
        List<Course> courses = studentService.getCourses(id);
        return courses.stream().map(CourseDTO::toDTO).toList();
    }

}
