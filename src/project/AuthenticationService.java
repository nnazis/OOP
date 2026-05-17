package project;
import java.util.List;

public class AuthenticationService {
	private University university;
	
	public AuthenticationService(University university) {
		this.university = university;
	}
	
	public User login(String username, String password) throws AuthenticationException {
		for (User user : university.getUsers()) {
			if (user.getUsername().equals(username) &&
					user.getPassword().equals(password)) {
				
				user.login(username, password);
				return user;
			}
		}
		
		throw new AuthenticationException("Invalid username of password");
	}
	
	public void logout(User user) {
		if (user != null) {
			user.logout();
		}
	}
}
