import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Report {

    private String reportId;
    private ReportType reportType;
    private String title;
    private String content;
    private String createdAt;

    public Report(ReportType reportType, String title, String content) {
        this.reportId = UUID.randomUUID().toString();
        this.reportType = reportType;
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public Report(String reportId, ReportType reportType,String title, String content, String createdAt) {
        this.reportId = reportId;
        this.reportType = reportType;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public String getReportId(){ 
        return reportId;
    }
    public ReportType getReportType(){
        return reportType;
    }
    public String getTitle()
    { return title;

    }
    public String getContent()
    { return content;
    }
    public String getCreatedAt(){ 
        return createdAt;
    }

    public void printReport() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "==========================================\n"
             + "REPORT: " + title + "\n"
             + "Type   : " + reportType + "\n"
             + "Created: " + createdAt + "\n"
             + "──────────────────────────────────────────\n"
             + content + "\n"
             + "==========================================\n";
    }
}
