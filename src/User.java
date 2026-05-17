
import java.io.Serializable;

public class User implements Serializable {
    private String id;
    private String name;
    private String email;

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return name;
    }

    public void viewMenu() {
        System.out.println("Menu for " + name);
    }

    @Override
    public String toString() {
        return name + " (" + id + ")";
    }
}
