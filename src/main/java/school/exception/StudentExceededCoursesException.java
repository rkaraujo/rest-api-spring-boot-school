package school.exception;

public class StudentExceededCoursesException extends RuntimeException {

    public StudentExceededCoursesException(String message) {
        super(message);
    }
}
