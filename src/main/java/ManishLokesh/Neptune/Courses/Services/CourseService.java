package ManishLokesh.Neptune.Courses.Services;

import ManishLokesh.Neptune.Courses.Entity.Course;

import java.util.List;

public interface CourseService {

    public List<Course> getCourses();

    public Course getCourse(Long courseId);

    public  Course addCourse(Course course);

    public Course updateCourse(Course course);

   public void deleteCourse(long parseLong);
}
