package school.exception;

public class CourseExceededStudentsException extends RuntimeException {

    public CourseExceededStudentsException(String message) {
        super(message);
    }
}
