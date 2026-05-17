package project;

public class UserFactory {
	public static User createUser(UserRole role, String id, String username,
			String password, String firstName, String lastName, String email) {
		
		switch (role) {
		
		case STUDENT:
			return new Student(id, username, password, firstName, lastName, email);
			
		case TEACHER: 
			return new Teacher(id, username, password, firstName, lastName, email);
		
		case ADMIN:
			return new Admin(id, username, password, firstName, lastName, email);
			
		case MANAGER:
			return new Manager(id, username, password, firstName, lastName, email);
			
		default:
			return null;
		}
	}
}
