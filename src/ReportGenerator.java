
import java.util.List;

public interface ReportGenerator {
	Report generateStudentPerformanceReport(List<Student> students);
	Report generateCourseStatisticsReport(Course course);
	Report generateTeacherStatisticsReport(Teacher teahcer);
	Report generateResearchStatisticsReport(List<Researcher> researchers);
}
