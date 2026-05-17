import java.io.Serializable;

public class SystemSettings implements Serializable {
    private String universityName;
    private boolean registrationOpen;
    private boolean researchModuleOpen;
    private int maxCreditsPerSemester;
    private String academicYear;

    public SystemSettings() {
        this.universityName = "University Information System";
        this.registrationOpen = true;
        this.researchModuleOpen = true;
        this.maxCreditsPerSemester = 21;
        this.academicYear = "2025-2026";
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        if (universityName == null || universityName.isBlank()) {
            throw new IllegalArgumentException("University name cannot be empty");
        }
        this.universityName = universityName;
    }

    public boolean isRegistrationOpen() {
        return registrationOpen;
    }

    public void setRegistrationOpen(boolean registrationOpen) {
        this.registrationOpen = registrationOpen;
    }

    public boolean isResearchModuleOpen() {
        return researchModuleOpen;
    }

    public void setResearchModuleOpen(boolean researchModuleOpen) {
        this.researchModuleOpen = researchModuleOpen;
    }

    public int getMaxCreditsPerSemester() {
        return maxCreditsPerSemester;
    }

    public void setMaxCreditsPerSemester(int maxCreditsPerSemester) {
        if (maxCreditsPerSemester <= 0) {
            throw new IllegalArgumentException("Maximum credits must be positive");
        }
        this.maxCreditsPerSemester = maxCreditsPerSemester;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        if (academicYear == null || academicYear.isBlank()) {
            throw new IllegalArgumentException("Academic year cannot be empty");
        }
        this.academicYear = academicYear;
    }

    @Override
    public String toString() {
        return "SystemSettings{" +
                "universityName='" + universityName + '\'' +
                ", registrationOpen=" + registrationOpen +
                ", researchModuleOpen=" + researchModuleOpen +
                ", maxCreditsPerSemester=" + maxCreditsPerSemester +
                ", academicYear='" + academicYear + '\'' +
                '}';
    }
}
