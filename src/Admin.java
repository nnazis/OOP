
import java.util.ArrayList;
import java.util.List;

public class Admin extends Employee {
	private List<LogRecord> logs = new ArrayList<>();
	private List<Complaint> complaints = new ArrayList<>();
	
	public Admin(String id, String username, String password, String firstName, String lastName,
			String email, double salary, String hireDate) {
		super(id, username, password, firstName, lastName, email, salary, hireDate, UserRole.ADMIN);
	}

	public void addUser(User user) {
		if (user == null) {
			throw new IllegalArgumentException("User cannot be null");
		}
		if (findUserById(user.getId()) != null) {
			throw new IllegalArgumentException("User with this id already exists");
		}
		University.getInstance().addUser(user);
		recordAction("ADD_USER", user, "Added user " + user.getFullName());
	}

	public void removeUser(User user) {
		if (user == null) {
			throw new IllegalArgumentException("User cannot be null");
		}
		University.getInstance().removeUser(user);
		recordAction("REMOVE_USER", user, "Removed user " + user.getFullName());
	}

	public void removeUserById(String userId) {
		User user = findUserById(userId);
		if (user == null) {
			throw new IllegalArgumentException("User was not found");
		}
		removeUser(user);
	}

	public void updateUserInfo(String userId, User updatedUser) {
		if (updatedUser == null) {
			throw new IllegalArgumentException("Updated user cannot be null");
		}
		User currentUser = findUserById(userId);
		if (currentUser == null) {
			throw new IllegalArgumentException("User was not found");
		}
		University university = University.getInstance();
		university.removeUser(currentUser);
		university.addUser(updatedUser);
		recordAction("UPDATE_USER_INFO", updatedUser, "Updated user info");
	}

	public void updateUser(User user) {
		updateUserInfo(user.getId(), user);
	}

	public User findUserById(String userId) {
		for (User user : University.getInstance().getUsers()) {
			if (user.getId().equals(userId)) {
				return user;
			}
		}
		return null;
	}

	public List<User> viewUsers() {
		return University.getInstance().getUsers();
	}

	public void printUsers() {
		viewUsers().forEach(System.out::println);
	}

	public void viewLogs() {
		getLogs().forEach(System.out::println);
	}

	public List<LogRecord> getLogs() {
		return University.getInstance().getLogRecords();
	}

	public List<LogRecord> monitorUserActions(User user) {
		if (user == null) {
			throw new IllegalArgumentException("User cannot be null");
		}
		List<LogRecord> result = new ArrayList<>();
		for (LogRecord log : University.getInstance().getLogRecords()) {
			if (isSameUser(log.getUser(), user) || isSameUser(log.getTargetUser(), user)) {
				result.add(log);
			}
		}
		return result;
	}

	public void printUserActions(User user) {
		monitorUserActions(user).forEach(System.out::println);
	}

	public void receiveComplaint(Complaint complaint) {
		if (complaint == null) {
			throw new IllegalArgumentException("Complaint cannot be null");
		}
		complaints.add(complaint);
		recordAction("RECEIVE_COMPLAINT", complaint.getSender(),
				"Received complaint from " + complaint.getSender().getFullName());
	}

	public List<Complaint> getComplaints() {
		return complaints;
	}

	public void viewComplaints() {
		complaints.forEach(System.out::println);
	}

	public SystemSettings getSystemSettings() {
		return University.getInstance().getSystemSettings();
	}

	public void renameUniversity(String universityName) {
		getSystemSettings().setUniversityName(universityName);
		recordAction("UPDATE_SYSTEM_SETTINGS", this, "Changed university name to " + universityName);
	}

	public void setRegistrationOpen(boolean open) {
		getSystemSettings().setRegistrationOpen(open);
		recordAction("UPDATE_SYSTEM_SETTINGS", this, "Course registration open: " + open);
	}

	public void setResearchModuleOpen(boolean open) {
		getSystemSettings().setResearchModuleOpen(open);
		recordAction("UPDATE_SYSTEM_SETTINGS", this, "Research module open: " + open);
	}

	public void setMaxCreditsPerSemester(int maxCredits) {
		getSystemSettings().setMaxCreditsPerSemester(maxCredits);
		recordAction("UPDATE_SYSTEM_SETTINGS", this, "Max credits per semester: " + maxCredits);
	}

	public void setAcademicYear(String academicYear) {
		getSystemSettings().setAcademicYear(academicYear);
		recordAction("UPDATE_SYSTEM_SETTINGS", this, "Academic year: " + academicYear);
	}

	private void recordAction(String action, User targetUser, String description) {
		LogRecord log = new LogRecord(action, this, targetUser, description);
		logs.add(log);
		University.getInstance().addLogRecord(log);
	}

	private boolean isSameUser(User first, User second) {
		return first != null && second != null && first.getId().equals(second.getId());
	}

	@Override
	public void viewMenu() {
		super.viewMenu();
		System.out.println("3. Add user");
		System.out.println("4. Remove user");
		System.out.println("5. Update user info");
		System.out.println("6. View log files");
		System.out.println("7. Monitor user actions");
		System.out.println("8. Manage system settings");
		System.out.println("9. View complaints");
	}

	@Override
	public String toString() {
		return "Admin " + getFullName() + " (" + getId() + ")";
	}
}
