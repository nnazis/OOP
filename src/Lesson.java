
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class Lesson implements Serializable {
    private LessonType type;
    private DayOfWeek day;
    private LocalTime time;
    private String room;
    private Teacher teacher;
    private Course course;

    public Lesson(LessonType type, String day, String time, String room, Teacher teacher, Course course) {
        this(type, DayOfWeek.valueOf(day.toUpperCase()), LocalTime.parse(time), room, teacher, course);
    }

    public Lesson(LessonType type, DayOfWeek day, LocalTime time, String room, Teacher teacher, Course course) {
        this.type = type;
        this.day = day;
        this.time = time;
        this.room = room;
        this.teacher = teacher;
        this.course = course;
    }

    public LessonType getType() {
        return type;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getRoom() {
        return room;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Course getCourse() {
        return course;
    }

    @Override
    public String toString() {
        return type + " " + day + " " + time + " room " + room;
    }
}
