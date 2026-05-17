import java.util.ArrayList;
import java.util.List;

public class Admin extends Employee {
    private List<LogRecord> logs;

    public Admin(String id, String username, String password, String firstName, String lastName, String email,double salary, String hireDate) {
        super(id, username, password, firstName, lastName, email, UserRole.ADMIN, salary, hireDate);
        this.logs = new ArrayList<>();
    }

    public List<LogRecord> getLogs() { return logs; }

    public void addUser(User user) {
        System.out.println("User added: " + user.getFullName());
    }

    public void removeUser(User user) {
        System.out.println("User removed: " + user.getFullName());
    }

    public void updateUser(User user) {
        System.out.println("User updated: " + user.getFullName());
    }

    public List<LogRecord> viewLogs() {
        return logs;
    }

    public void viewMenu() {
        System.out.println("Admin menu");
    }

    public String toString() {
        return "Admin[id=" + getId() + ", name=" + getFullName() + "]";
    }
}
