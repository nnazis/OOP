
import java.util.ArrayList;
import java.util.List;

public class Admin extends Employee{
	private List<LogRecord> logs = new ArrayList<>();
	
	public Admin(String id, String username, String password, String firstName, String lastName,
			String email, double salary, String hireDate) {
		super(id, username, password, firstName, lastName, email, salary, hireDate, UserRole.ADMIN);
	}
	public void addUser(User user) {
		University.getInstance().addUser(user);
		logs.add(new LogRecord("ADD_USER", this, "Added " + user.getFullName()));
	}
	public void removeUser(User user) {
		University.getInstance().removeUser(user);
	}
	public void viewLogs() {
		logs.forEach(System.out::println); 
	}
}
