package ManishLokesh.Neptune.Controller;


import ManishLokesh.Neptune.Doa.CourseDoa;
import ManishLokesh.Neptune.Entity.Course;
import ManishLokesh.Neptune.Services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyController{

    @Autowired
    private CourseService service;


    // get home page
    @GetMapping("/home")
    public String home(){
        return "Welcome to home page";
    }

    // get all courses
    @GetMapping("/courses")
    public List<Course> getCourses() {
        return this.service.getCourses();
    }

    //get single course
    @GetMapping("/courses/{courseId}")
    public Course getCourse(@PathVariable String courseId){
        return this.service.getCourse(Long.parseLong(courseId));
    }

    // create course
    @PostMapping(path = "/courses", consumes = "application/json")
    public Course addCourse(@RequestBody Course course){
        return this.service.addCourse(course);
    }

    // update course
    @PutMapping("/courses")
    public Course updateCourse(@RequestBody Course course){
        return this.service.updateCourse(course);
    }

    // delete course
    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable String courseId){
        try {
            this.service.deleteCourse(Long.parseLong(courseId));
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
