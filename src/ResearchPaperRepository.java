public class ResearchPaperRepository extends DataRepository<ResearchPaper> {
    public ResearchPaper findById(String id) {
        for (ResearchPaper paper : items) {
            if (paper.getId().equals(id)) {
                return paper;
            }
        }
        return null;
    }

    public ResearchPaper findByDoi(String doi) {
        for (ResearchPaper paper : items) {
            if (paper.getDoi().equals(doi)) {
                return paper;
            }
        }
        return null;
    }
}
