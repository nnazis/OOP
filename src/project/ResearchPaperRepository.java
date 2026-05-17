package project;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ResearchPaperRepository implements DataRepository<ResearchPaper>{
	
	private List<ResearchPaper> papers;
	
	public ResearchPaperRepository() {
		this.papers = new ArrayList<>();
	}
	@Override
	public void save(ResearchPaper obj) {
		papers.add(obj);
	}
	@Override
	public void delete(ResearchPaper obj) {
		papers.remove(obj);
	}
	@Override
	public List<ResearchPaper> findAll() {
		return new ArrayList<>(papers);
	}
	@Override
	public Optional<ResearchPaper> findById(String id) {
		for (ResearchPaper paper : papers) {
			if (paper.getId().equals(id)) {
				return Optional.of(paper);
			}
		}
		return Optional.empty();
	}
	
}
