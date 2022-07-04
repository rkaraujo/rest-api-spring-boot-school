# Class registration

## Information

The system was developed with:

* Java 17
* Spring Boot
* Maven
* MySQL

## Run the application

~~~
docker-compose up
~~~

## Endpoints

### Create course
~~~
POST http://localhost:6868/v1/course

Body example:
{
    "name": "Test",
    "code": "TST-101"
}
~~~

### Create student
~~~
POST http://localhost:6868/v1/student

Body example:
{
    "name": "John Doe"
}
~~~

### Create student
~~~
PUT http://localhost:6868/v1/student/{studentId}/register-to-course/{courseId}

Example:
PUT http://localhost:6868/v1/student/1/register-to-course/1
~~~

### Get student courses
~~~
GET http://localhost:6868/v1/student/{studentId}/courses

Example:
GET http://localhost:6868/v1/student/1/courses
~~~

## Get course students
~~~
GET http://localhost:6868/v1/course/{courseId}/students

Example:
GET http://localhost:6868/v1/course/1/students
~~~


