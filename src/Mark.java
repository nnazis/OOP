
import java.io.Serializable;

public class Mark implements Serializable {
    private double attestation1;
    private double attestation2;
    private double finalExam;
    private double total;
    private String letterGrade;

    private Assessment assessment;
    private double score;

    public Mark() {
        this(0, 0, 0);
    }

    public Mark(double attestation1, double attestation2, double finalExam) {
        setScores(attestation1, attestation2, finalExam);
    }

    public Mark(Assessment assessment, double score) {
        if (assessment == null) {
            throw new IllegalArgumentException("Assessment cannot be null!");
        }
        if (score < 0 || score > assessment.getMaxScore()) {
            throw new IllegalArgumentException("Score must be between 0 and assessment max score!");
        }
        this.assessment = assessment;
        this.score = score;
    }

    public void setScores(double attestation1, double attestation2, double finalExam) {
        validateRange(attestation1, 0, 30, "Attestation 1");
        validateRange(attestation2, 0, 30, "Attestation 2");
        validateRange(finalExam, 0, 40, "Final exam");
        this.attestation1 = attestation1;
        this.attestation2 = attestation2;
        this.finalExam = finalExam;
        calculateTotal();
        calculateLetterGrade();
    }

    private void validateRange(double value, double min, double max, String fieldName) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(fieldName + " must be between " + min + " and " + max + "!");
        }
    }

    private void calculateTotal() {
        this.total = attestation1 + attestation2 + finalExam;
    }

    private void calculateLetterGrade() {
        if (total >= 94.5) letterGrade = "A";
        else if (total >= 89.5) letterGrade = "A-";
        else if (total >= 84.5) letterGrade = "B+";
        else if (total >= 79.5) letterGrade = "B";
        else if (total >= 74.5) letterGrade = "B-";
        else if (total >= 69.5) letterGrade = "C+";
        else if (total >= 64.5) letterGrade = "C";
        else if (total >= 59.5) letterGrade = "C-";
        else if (total >= 54.5) letterGrade = "D+";
        else if (total >= 49.5) letterGrade = "D";
        else letterGrade = "F";
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

    public Assessment getAssessment() {
        return assessment;
    }

    public double getScore() {
        return score;
    }

    public double getMaxScore() {
        return assessment.getMaxScore();
    }

    public MarkPeriod getPeriod() {
        return assessment.getPeriod();
    }

    @Override
    public String toString() {
        return "Mark{attestation1=" + attestation1 +
                ", attestation2=" + attestation2 +
                ", finalExam=" + finalExam +
                ", total=" + total +
                ", letterGrade='" + letterGrade + "'}";
    }
}
