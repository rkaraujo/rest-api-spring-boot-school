package school.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import school.exception.CourseExceededStudentsException;
import school.exception.StudentExceededCoursesException;
import school.model.Course;
import school.model.Student;
import school.repository.CourseRepository;
import school.repository.StudentRepository;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StudentServiceTest {

    private StudentRepository studentRepository;
    private CourseRepository courseRepository;
    private StudentService studentService;

    @BeforeEach
    public void setUp() {
        this.studentRepository = mock(StudentRepository.class);
        this.courseRepository = mock(CourseRepository.class);
        this.studentService = new StudentService(studentRepository, courseRepository);
    }

    @Test
    public void checkStudentCoursesLimit() {
        int maximumCoursesByStudent = 5;

        Student student = new Student();
        student.setId(1);
        student.setName("Name");

        int i;
        for (i = 0; i < maximumCoursesByStudent; i++) {
            Course course = new Course();
            course.setId(i);
            course.setName("Course " + i);
            student.getCourses().add(course);
        }
        Integer exceededCourseId = i + 1;

        when(studentRepository.getReferenceById(anyInt())).thenReturn(student);

        Assertions.assertThrows(
                StudentExceededCoursesException.class,
                () -> studentService.registerToCourse(student.getId(), exceededCourseId)
        );
    }

    @Test
    public void checkCourseStudentsLimit() {
        int maximumStudentsByCourse = 50;

        Course course = new Course();
        course.setId(1);
        course.setName("Course");
        course.setCode("C-101");

        int i;
        for (i = 0; i < maximumStudentsByCourse; i++) {
            Student student = new Student();
            student.setId(i);
            student.setName("Student " + i);
            course.getStudents().add(student);
        }

        Student exceededStudent = new Student();
        exceededStudent.setId(i + 1);
        exceededStudent.setName("Exceeded Student");

        when(studentRepository.getReferenceById(anyInt())).thenReturn(exceededStudent);
        when(courseRepository.getReferenceById(anyInt())).thenReturn(course);

        Assertions.assertThrows(
                CourseExceededStudentsException.class,
                () -> studentService.registerToCourse(exceededStudent.getId(), course.getId())
        );
    }
}
