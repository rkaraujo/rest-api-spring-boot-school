package school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import school.dto.CourseDTO;
import school.model.Course;
import school.service.CourseService;

import javax.validation.Valid;

@RestController
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/v1/course")
    public CourseDTO create(@Valid @RequestBody CourseDTO courseDTO) {
        Course createdCourse = courseService.create(toEntity(courseDTO));
        return toDTO(createdCourse);
    }

    private CourseDTO toDTO(Course course) {
        return new CourseDTO(course.getId(), course.getName(), course.getCode());
    }

    private Course toEntity(CourseDTO courseDTO) {
        Course course = new Course();
        course.setName(courseDTO.name());
        course.setCode(courseDTO.code());
        return course;
    }

}
