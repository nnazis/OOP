public class AuthenticationService {
    private University university;

    public AuthenticationService(University university) {
        this.university = university;
    }

    public User login(String username, String password) throws AuthenticationException {
        return university.authenticate(username, password);
    }
}

