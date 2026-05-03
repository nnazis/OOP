
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResearchProject implements Serializable {
    private String title;
    private List<User> participants;

    public ResearchProject(String title) {
        this.title = title;
        this.participants = new ArrayList<>();
    }

    public void addParticipant(User user) {
        if (!participants.contains(user)) {
            participants.add(user);
        }
    }

    public String getTitle() {
        return title;
    }

    public List<User> getParticipants() {
        return participants;
    }
}
