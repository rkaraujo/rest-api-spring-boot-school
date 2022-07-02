package school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import school.dto.CourseDTO;
import school.dto.StudentDTO;
import school.model.Course;
import school.model.Student;
import school.service.CourseService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/v1/course")
    public CourseDTO create(@Valid @RequestBody CourseDTO courseDTO) {
        Course createdCourse = courseService.create(courseDTO.toEntity());
        return CourseDTO.toDTO(createdCourse);
    }

    @GetMapping("/v1/course/{id}/students")
    public List<StudentDTO> getStudents(@PathVariable Integer id) {
        List<Student> students = courseService.getStudents(id);
        return students.stream().map(StudentDTO::toDTO).toList();
    }
}
