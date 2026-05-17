public class User {
    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private UserRole role;
    private boolean authenticated;

    public User(String id, String username, String password, String firstName, String lastName, String email, UserRole role) {
        this.id = id;
        this.username=username;
        this.password=password;
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.role=role;
        this.authenticated=false;
    }

    public String getId(){
        return id;
    }
    public String getUsername(){ 
        return username;
    }
    public String getPassword(){
        return password; 
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getEmail(){ 
        return email;
    }
    public UserRole getRole(){
        return role;
    }
    public boolean isAuthenticated(){
        return authenticated;
    }

    public void login(String username, String password) throws AuthenticationException {
        if (!this.username.equals(username) || !this.password.equals(password)) {
            throw new AuthenticationException("Wrong username or password");
        }
        this.authenticated = true;
    }

    public void logout() {
        this.authenticated = false;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void viewMenu() {
        System.out.println("User menu");
    }

    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (!(obj instanceof User)){
            return false;
        }
        User other = (User) obj;
        return this.id.equals(other.id);
    }

    public int hashCode() {
        return id.hashCode();
    }

    public String toString() {
        return "User[id=" + id + ", username=" + username + ", role=" + role + "]";
    }
}
