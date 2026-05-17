import java.util.ArrayList;
import java.util.List;

public class ResearchProject {
    private String projectId;
    private String topic;
    private String description;
    private String startDate;
    private String endDate;
    private List<Researcher> participants;

    public ResearchProject(String projectId, String topic, String description, String startDate, String endDate) {
        this.projectId = projectId;
        this.topic = topic;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.participants = new ArrayList<>();
    }

    public String getProjectId(){
        return projectId;
    }
    public String getTopic(){
        return topic;
    }
    public String getTitle(){
        return topic;
    }
    public String getDescription(){
        return description;
    }
    public String getStartDate(){
        return startDate;
    }
    public String getEndDate(){
        return endDate;
    }

    public List<Researcher> getParticipants() {
        return participants;
    }

    public void addParticipant(Researcher r) {
        if (r != null && !participants.contains(r)) {
            participants.add(r);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (!(obj instanceof ResearchProject)){
            return false;
        }
        return projectId.equals(((ResearchProject) obj).projectId);
    }

    public int hashCode() { return projectId.hashCode(); }

    public String toString() {
        return "ResearchProject[id=" + projectId + ", topic=" + topic + "]";
    }
}
