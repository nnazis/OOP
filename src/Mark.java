public class Mark {
    private double attestation1;
    private double attestation2;
    private double finalExam;
    private double total;
    private String letterGrade;

    public Mark(double attestation1, double attestation2, double finalExam) {
        this.attestation1 = attestation1;
        this.attestation2 = attestation2;
        this.finalExam = finalExam;
        calculateTotal();
        calculateLetterGrade();
    }

    public double getAttestation1() { 
        return attestation1; 
    }
    public double getAttestation2() { 
        return attestation2; 
    }
    public double getFinalExam(){
        return finalExam; 
    }
    public double getTotal(){
        return total;
    }
    public String getLetterGrade(){
        return letterGrade;
    }

    public void setAttestation1(double attestation1) {
        this.attestation1 = attestation1;
        calculateTotal();
        calculateLetterGrade();
    }

    public void setAttestation2(double attestation2) {
        this.attestation2 = attestation2;
        calculateTotal();
        calculateLetterGrade();
    }

    public void setFinalExam(double finalExam) {
        this.finalExam = finalExam;
        calculateTotal();
        calculateLetterGrade();
    }

    public void calculateTotal() {
        total = attestation1 + attestation2 + finalExam;
    }

    public void calculateLetterGrade() {
        if (total >= 90) {
            letterGrade = "A";
        }
        else if (total >= 80){
            letterGrade = "B";
        }
        else if (total >= 70){
            letterGrade = "C";
        }
        else if (total >= 60){
            letterGrade = "D";
        }
        else {
            letterGrade = "F";
        }
    }

    public boolean isPassed() {
        return total >= 50;
    }

    public String toString() {
        return "Mark[att1=" + attestation1 + ", att2=" + attestation2 + ", final=" + finalExam + ", total=" + total + ", grade=" + letterGrade + "]";
    }
}
