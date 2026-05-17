import java.util.ArrayList;
import java.util.List;


public class ReportPage {

    private List<Report> reportList;

    public ReportPage() {
        this.reportList = new ArrayList<>();
    }

    public void addReport(Report report) {
        reportList.add(report);
        System.out.println("Report saved: \"" + report.getTitle() + "\"");
    }

    public void removeReport(Report report) {
        if (reportList.remove(report)) {
            System.out.println("Report removed: \"" + report.getTitle() + "\"");
        } else {
            System.out.println("Report not found.");
        }
    }

    public List<Report> getAllReports() {
        return reportList;
    }

    public List<Report> getReportsByType(ReportType type) {
        List<Report> result = new ArrayList<>();
        for (Report r : reportList) {
            if (r.getReportType() == type) {
                result.add(r);
            }
        }
        return result;
    }

    public Report findById(String reportId) {
        for (Report r : reportList) {
            if (r.getReportId().equals(reportId)) return r;
        }
        return null;
    }

    public void printAll() {
        if (reportList.isEmpty()) {
            System.out.println("No reports available.");
            return;
        }
        System.out.println("\n===== ALL REPORTS =====");
        for (int i = 0; i < reportList.size(); i++) {
            Report r = reportList.get(i);
            System.out.println("[" + (i + 1) + "] ["
                    + r.getReportType() + "] "
                    + r.getTitle()
                    + " | " + r.getCreatedAt());
        }
    }

    public void printReport(int index) {
        if (index < 1 || index > reportList.size()) {
            System.out.println("Invalid report number.");
            return;
        }
        reportList.get(index - 1).printReport();
    }

    public void printByType(ReportType type) {
        List<Report> filtered = getReportsByType(type);
        if (filtered.isEmpty()) {
            System.out.println("No reports of type: " + type);
            return;
        }
        System.out.println("\n===== REPORTS: " + type + " =====");
        for (int i = 0; i < filtered.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + filtered.get(i).getTitle()
                    + " | " + filtered.get(i).getCreatedAt());
        }
    }
}
