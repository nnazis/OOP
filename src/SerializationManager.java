import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationManager {
    private String filePath;

    public SerializationManager(String filePath) {
        this.filePath = filePath;
    }

    public void saveUniversity(University university) {
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filePath));
            output.writeObject(university);
            output.close();
        } catch (IOException e) {
            System.out.println("Could not save university data: " + e.getMessage());
        }
    }

    public University loadUniversity() {
        File file = new File(filePath);
        if (!file.exists()) {
            return new University();
        }

        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(filePath));
            University university = (University) input.readObject();
            input.close();
            University.setInstance(university);
            return university;
        } catch (IOException e) {
            System.out.println("Could not load university data: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Saved file has wrong data: " + e.getMessage());
        }
        return new University();
    }
}
