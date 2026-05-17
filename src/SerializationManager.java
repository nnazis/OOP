import java.io.*;

public class SerializationManager {
    private String filePath;

    public SerializationManager(String filePath) {
        this.filePath = filePath;
    }

    public void saveUniversity(University university) {
        System.out.println("Saving university to " + filePath);
    }

    public University loadUniversity() {
        System.out.println("Loading university from " + filePath);
        return null;
    }
}

