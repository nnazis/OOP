import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ResearchPaperRepository implements DataRepository<ResearchPaper> {
    private List<ResearchPaper> papers;

    public ResearchPaperRepository(){
        papers = new ArrayList<>();
    }

    public void save(ResearchPaper obj){
        if (!papers.contains(obj)){
            papers.add(obj);
        }
    }
    public void delete(ResearchPaper obj){
        papers.remove(obj);
    }
    public List<ResearchPaper> findAll(){
        return papers;
    }

    public Optional<ResearchPaper> findById(String id) {
        for (ResearchPaper p : papers) {
            if (p.getPaperId().equals(id)){
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }
}
