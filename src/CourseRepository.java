import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseRepository implements DataRepository<Course> {
    private List<Course> courses;

    public CourseRepository() {
        courses = new ArrayList<>();
    }

    public void save(Course obj){
        if (!courses.contains(obj)){
            courses.add(obj);
        }
    }
    public void delete(Course obj) {
        courses.remove(obj);

    }
    public List<Course> findAll(){ 
        return courses; 
    }

    public Optional<Course> findById(String id) {
        for (Course c : courses) {
            if (c.getCourseId().equals(id)){
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }
}
