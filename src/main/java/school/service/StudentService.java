package school.service;

import org.springframework.stereotype.Service;
import school.model.Course;
import school.model.Student;
import school.repository.CourseRepository;
import school.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public Student create(Student student) {
        return studentRepository.save(student);
    }

    public void registerToCourse(Integer studentId, Integer courseId) {
        Student student = studentRepository.getReferenceById(studentId);
        Course course = courseRepository.getReferenceById(courseId);
        student.getCourses().add(course);
        studentRepository.save(student);
    }

    public List<Course> getCourses(Integer id) {
        Student student = studentRepository.getReferenceById(id);
        return student.getCourses();
    }
}
