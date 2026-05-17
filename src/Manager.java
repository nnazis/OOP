
import java.util.List;

public class Manager extends Employee implements ReportGenerator{
	private ManagerType managerType;
	public Manager(String id, String username, String password, String firstName, String lastName,
			String email,double salary, String hireDate, ManagerType managerType) {
		super(id, username, password, firstName, lastName, email, salary, hireDate, UserRole.MANAGER);
		this.managerType = managerType;
	}
	public ManagerType getManagerType() {
		return managerType;
	}
	public void approveRegistration(Student student, Course course) {
		System.out.println("Rejected registration for " + student.getFullName());
	}
	
	@Override
	public Report generateStudentPerformanceReport(List<Student> students) {
		System.out.println("Student Performance Report");
		return new Report("Student Stats", "Performance data");
	}
	@Override
	public Report generateCourseStatisticsReport(Course course) {
		return new Report("Course Stats", "");
	}
	@Override
	public Report generateTeacherStatisticsReport(Teacher teacher) {
		return new Report ("Teacher Stats", "");
	}
	@Override
	public Report generateResearchStatisticsReport(List<Researcher> researchers) {
		return new Report("Research Stats", "");
	}
}
