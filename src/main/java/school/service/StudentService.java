package school.service;

import org.springframework.stereotype.Service;
import school.exception.CourseExceededStudentsException;
import school.exception.StudentExceededCoursesException;
import school.model.Course;
import school.model.Student;
import school.repository.CourseRepository;
import school.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {

    public static final int MAXIMUM_COURSES_BY_STUDENT = 5;
    public static final int MAXIMUM_STUDENTS_BY_COURSE = 50;

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

        List<Course> studentCourses = student.getCourses();
        boolean isAlreadyRegistered = studentCourses.stream().anyMatch(course -> course.getId().equals(courseId));
        if (isAlreadyRegistered) {
            return;
        }

        if (studentCourses.size() == MAXIMUM_COURSES_BY_STUDENT) {
            throw new StudentExceededCoursesException("Student " + studentId + " can't register to more than " + MAXIMUM_COURSES_BY_STUDENT + " courses");
        }

        Course course = courseRepository.getReferenceById(courseId);
        if (course.getStudents().size() == MAXIMUM_STUDENTS_BY_COURSE) {
            throw new CourseExceededStudentsException("Course " + courseId + " can't register more than " + MAXIMUM_STUDENTS_BY_COURSE + " students");
        }

        studentCourses.add(course);
        studentRepository.save(student);
    }

    public List<Course> getCourses(Integer id) {
        Student student = studentRepository.getReferenceById(id);
        return student.getCourses();
    }
}
