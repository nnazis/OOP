
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TotalMark implements Serializable {
    private double attestation1;
    private double attestation2;
    private double finalExam;
    private double total;
    private String letterGrade;
    private GradingPolicy gradingPolicy;
    private List<Mark> marks;
    private List<Assessment> assessments;

    public TotalMark(GradingPolicy gradingPolicy, List<Assessment> assessments) {
        if (gradingPolicy == null) {
            throw new IllegalArgumentException("Grading policy can't be null!");
        }
        if (assessments == null) {
            throw new IllegalArgumentException("Assessments cannot be null!");
        }
        this.gradingPolicy = gradingPolicy;
        this.assessments = new ArrayList<>(assessments);
        this.marks = new ArrayList<>();
        recalculate();
    }

    public double getAttestation1() {
        return attestation1;
    }

    public double getAttestation2() {
        return attestation2;
    }

    public double getFinalExam() {
        return finalExam;
    }

    public double getTotal() {
        return total;
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public GradingPolicy getGradingPolicy() {
        return gradingPolicy;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public List<Assessment> getAssessments() {
        return assessments;
    }

    public void addMark(Mark mark) {
        if (mark == null) {
            throw new IllegalArgumentException("Mark cannot be null!");
        }
        if (!assessments.contains(mark.getAssessment())) {
            throw new IllegalArgumentException("This mark doesn't belong to an assessment from this course!");
        }
        marks.add(mark);
        recalculate();
    }

    public void removeMark(Mark mark) {
        marks.remove(mark);
        recalculate();
    }

    public void clearMarks() {
        marks.clear();
        recalculate();
    }

    private double convertToWeightedScore(double score, double maxScore, double weight) {
        if (maxScore == 0) {
            return 0;
        }
        return (score / maxScore) * weight;
    }

    public void recalculate() {
        double att1Score = 0;
        double att1Max = 0;
        double att2Score = 0;
        double att2Max = 0;
        double finalScore = 0;
        double finalMax = 0;

        for (Assessment assessment : assessments) {
            if (assessment.getPeriod() == MarkPeriod.ATTESTATION1) {
                att1Max += assessment.getMaxScore();
            } else if (assessment.getPeriod() == MarkPeriod.ATTESTATION2) {
                att2Max += assessment.getMaxScore();
            } else if (assessment.getPeriod() == MarkPeriod.FINAL) {
                finalMax += assessment.getMaxScore();
            }
        }

        for (Mark mark : marks) {
            if (mark.getPeriod() == MarkPeriod.ATTESTATION1) {
                att1Score += mark.getScore();
            } else if (mark.getPeriod() == MarkPeriod.ATTESTATION2) {
                att2Score += mark.getScore();
            } else if (mark.getPeriod() == MarkPeriod.FINAL) {
                finalScore += mark.getScore();
            }
        }

        this.attestation1 = convertToWeightedScore(
                att1Score,
                att1Max,
                gradingPolicy.getAttestation1Max()
        );

        this.attestation2 = convertToWeightedScore(
                att2Score,
                att2Max,
                gradingPolicy.getAttestation2Max()
        );

        this.finalExam = convertToWeightedScore(
                finalScore,
                finalMax,
                gradingPolicy.getFinalMax()
        );
        this.total = attestation1 + attestation2 + finalExam;
        this.letterGrade = calculateLetterGrade();
    }

    private String calculateLetterGrade() {
        if (!isCompleted()) return "N/A";
        if (total >= 94.5) return "A";
        if (total >= 89.5) return "A-";
        if (total >= 84.5) return "B+";
        if (total >= 79.5) return "B";
        if (total >= 74.5) return "B-";
        if (total >= 69.5) return "C+";
        if (total >= 64.5) return "C";
        if (total >= 59.5) return "C-";
        if (total >= 54.5) return "D+";
        if (total >= 49.5) return "D";
        return "F";
    }

    public boolean isCompleted() {
        return hasMarksForPeriod(MarkPeriod.ATTESTATION1)
                && hasMarksForPeriod(MarkPeriod.ATTESTATION2)
                && hasMarksForPeriod(MarkPeriod.FINAL);
    }

    private boolean hasMarksForPeriod(MarkPeriod period) {
        for (Mark mark : marks) {
            if (mark.getPeriod() == period) {
                return true;
            }
        }
        return false;
    }

    public boolean isPassed() {
        return isCompleted() && total >= 49.5;
    }

    @Override
    public String toString() {
        return "TotalMark{attestation1=" + attestation1 +
                ", attestation2=" + attestation2 +
                ", finalExam=" + finalExam +
                ", total=" + total +
                ", letterGrade='" + letterGrade + "'}";
    }
}