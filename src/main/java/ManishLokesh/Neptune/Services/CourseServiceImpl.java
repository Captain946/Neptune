package ManishLokesh.Neptune.Services;

import ManishLokesh.Neptune.Doa.CourseDoa;
import ManishLokesh.Neptune.Entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseDoa doa;

    //List<Course> list;

    public CourseServiceImpl(){
    //    list = new ArrayList<>();
    //    list.add(new Course(10L,"Manish Lohar","Learning"));
    //    list.add(new Course(20L,"Lokesh Lohar","Auditing"));
    }

    @Override
    public List<Course> getCourses(){
        return doa.findAll();
    }

    @Override
    public Course getCourse(Long courseId) {
       /* Course c = null;
        for(Course course : list){
            if(Objects.equals(course.getId(), courseId)){
                c = course;
                break;
            }
        }
        */
        return doa.getOne(courseId);
    }

    @Override
    public Course addCourse(Course course) {
        doa.save(course);
        //list.add(course);
        return course;
    }

    @Override
    public Course updateCourse(Course course) {
        //list.forEach(e ->{
          //  if(Objects.equals(e.getId(), course.getId())){
            //    e.setTitle(course.getTitle());
              //  e.setDescription(course.getDescription());
            //}
        //});

        doa.save(course);
        return course;
    }

    @Override
    public void deleteCourse(long parseLong) {
        //list = this.list.stream().filter(e -> e.getId() != parseLong).collect(Collectors.toList());

        Course entity = doa.getOne(parseLong);
        doa.delete(entity);
    }
}
