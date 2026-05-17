public class Lesson {
    private String lessonId;
    private LessonType lessonType;
    private String day;
    private String startTime;
    private String endTime;
    private String room;
    private Course course;
    private Teacher teacher;

    public Lesson(String lessonId, LessonType lessonType, String day, String startTime, String endTime, String room, Course course, Teacher teacher) {
        this.lessonId = lessonId;
        this.lessonType = lessonType;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.room = room;
        this.course = course;
        this.teacher = teacher;
    }

    public String getLessonId(){ 
        return lessonId; 
    }
    public LessonType getLessonType(){
        return lessonType;
    }
    public String getDay(){
        return day;
    }
    public String getStartTime(){
        return startTime;
    }
    public String getEndTime(){
        return endTime;
    }
    public String getRoom(){
        return room;
    }
    public Course getCourse(){
        return course;
    }
    public Teacher getTeacher(){
        return teacher;
    }

    public String getScheduleInfo() {
        return day + " " + startTime + "-" + endTime + " Room:" + room;
    }

    public String toString() {
        return "Lesson[" + lessonType + ", " + day + " " + startTime + "-" + endTime + "]";
    }
}
