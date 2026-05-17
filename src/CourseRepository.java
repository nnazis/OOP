
public class CourseRepository extends DataRepository<Course> {
    public Course findByCode(String code) {
        for (Course course : items) {
            if (course.getCode().equals(code)) {
                return course;
            }
        }
        return null;
    }
}
