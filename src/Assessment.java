
import java.io.Serializable;

public class Assessment implements Serializable {
    private String title;
    private double maxScore;
    private MarkPeriod period;

    public Assessment(String title, double maxScore, MarkPeriod period) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Assessment title cannot be empty!");
        }
        if (maxScore <= 0 || maxScore > 30) {
            throw new IllegalArgumentException("Max score for one assessment should be between 0 and 30!");
        }
        if (period == null) {
            throw new IllegalArgumentException("Period cannot be null!");
        }
        this.title = title;
        this.maxScore = maxScore;
        this.period = period;
    }

    public String getTitle() {
        return title;
    }

    public double getMaxScore() {
        return maxScore;
    }

    public MarkPeriod getPeriod() {
        return period;
    }
}