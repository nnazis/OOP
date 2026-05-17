package project;

public interface ReportGenerator {
	Report generateStudentPerformanceReport(List<Student> students);
	Report generateCourseStatisticsReport(Course course);
	Report generateTeacherStatisticsReport(Teacher teacher);
	Report generateResearchStatisticsReport(List<Researcher> researchers);
}
