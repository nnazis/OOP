package project;
import java.util.Objects;

public class User {
	protected String id;
	protected String username;
	protected String password;
	protected String firstName;
	protected String lastName;
	protected String email;
	protected UserRole role;
	protected boolean authenticated = false;
	
	public User(String id, String username, String password, String firstName, String lastName, String email, UserRole role) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
	}
	
	public String getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmail() {
		return email;
	}
	public UserRole getRole() {
		return role;
	}
	public boolean isAuthenticated() {
		return authenticated;
	}
	public void login(String username, String password) throws AuthenticationException {
		if (this.username.equals(username) && this.password.equals(password)) {
			this.authenticated = true;
		} else {
			throw new AuthenticationException("Invalid credentials");
		}
	}
	public void logout() {
		this.authenticated = false;
	}
	public String getFullName() {
		return firstName + " " + lastName;
	}
	public void viewMenu() {
		System.out.println("Menu for " + getFullName() + " (" + role + ")");
	}
	@Override
	public String toString() {
		return getFullName() + " (" + role + ")";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		User user = (User) obj;
		return id.equals(user.id);
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
