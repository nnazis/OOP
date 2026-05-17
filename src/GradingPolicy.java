
import java.io.Serializable;

public class GradingPolicy implements Serializable {
    public static final double FINAL_MAX = 40.0;
    public static final double ATTESTATIONS_TOTAL = 60.0;
    private double attestation1Max;
    private double attestation2Max;

    public GradingPolicy(double attestation1Max, double attestation2Max) {
        if (attestation1Max <= 0 || attestation2Max <= 0) {
            throw new IllegalArgumentException("Attestation scores must be greater than 0!");
        }
        if (attestation1Max + attestation2Max != ATTESTATIONS_TOTAL) {
            throw new IllegalArgumentException("Attestation1 + Attestation2 must equal 60!");
        }
        this.attestation1Max = attestation1Max;
        this.attestation2Max = attestation2Max;
    }

    public double getAttestation1Max() {
        return attestation1Max;
    }

    public double getAttestation2Max() {
        return attestation2Max;
    }

    public double getFinalMax() {
        return FINAL_MAX;
    }

    @Override
    public String toString() {
        return "GradingPolicy{attestation1Max=" + attestation1Max +
                ", attestation2Max=" + attestation2Max +
                ", finalMax=" + FINAL_MAX + "}";
    }
}