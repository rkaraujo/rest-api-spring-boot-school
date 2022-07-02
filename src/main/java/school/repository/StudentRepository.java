package school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
