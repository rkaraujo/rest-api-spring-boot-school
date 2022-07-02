package school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
