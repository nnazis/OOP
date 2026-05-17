import java.util.Scanner;

public class NewsReportConsole {

    private final Scanner scanner;
    private final NewsPage newsPage;
    private final ReportPage reportPage;

    public NewsReportConsole(NewsPage newsPage, ReportPage reportPage) {
        this.newsPage = newsPage;
        this.reportPage = reportPage;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;
        while (running) {
            printMainMenu();
            String input = scanner.nextLine().trim();
            switch (input) {
                case "1":
                    newsMenu();
                    break;
                case "2":
                    reportMenu();
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void printMainMenu() {
        System.out.println("""
                ╔══════════════════════════════╗
                ║     NEWS & REPORTS MENU      ║
                ╠══════════════════════════════╣
                ║  1. News                     ║
                ║  2. Reports                  ║
                ║  0. Back / Exit              ║
                ╚══════════════════════════════╝
                Choose:""");
    }

    private void newsMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("""
                    
                    --- NEWS ---
                    1. View all news
                    2. View news by category
                    3. Create news
                    0. Back
                    Choose:""");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    newsPage.printAll();
                    break;
                case "2":
                    viewNewsByCategory();
                    break;
                case "3":
                    createNews();
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void createNews() {
        NewsCategory category = pickNewsCategory();
        if (category == null){
            return;
        }
        System.out.print("Enter news title: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("Title cannot be empty.");
            return;
        }

        System.out.print("Enter author name: ");
        String author = scanner.nextLine().trim();
        if (author.isEmpty()){
            author = "Unknown";
        }

        String content = readMultiLineText(
                "Enter news text (each Enter = new line, type END to finish):"
        );
        if (content == null){
            return;
        }

        News news = new News(title, content, category, author);
        newsPage.addNews(news);
    }

    private NewsCategory pickNewsCategory() {
        NewsCategory[] categories = NewsCategory.values();
        System.out.println("Select news category:");
        for (int i = 0; i < categories.length; i++) {
            System.out.println("  " + (i + 1) + ". " + categories[i]);
        }
        System.out.print("Choose: ");
        try {
            int idx = Integer.parseInt(scanner.nextLine().trim());
            if (idx < 1 || idx > categories.length) {
                System.out.println("Invalid category number.");
                return null;
            }
            return categories[idx - 1];
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number.");
            return null;
        }
    }

    private void viewNewsByCategory() {
        NewsCategory category = pickNewsCategory();
        if (category == null) {
            return;
        }
        newsPage.printByCategory(category);
    }
    private void reportMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("""
                    
                    --- REPORTS ---
                    1. View all reports
                    2. View reports by type
                    3. View a specific report in full
                    4. Create report
                    0. Back
                    Choose:""");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    reportPage.printAll();
                    break;
                case "2":
                    viewReportsByType();
                    break;
                case "3":
                    viewSingleReport();
                    break;
                case "4":
                    createReport();
                    break;
                case "0":
                    back = true;
                    break;
                default:
                     System.out.println("Invalid choice.");
            }
        }
    }

    private void createReport() {
        ReportType type = pickReportType();
        if (type == null){
            return;
        }

        System.out.print("Enter report title: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("Title cannot be empty.");
            return;
        }

        String content = readMultiLineText(
                "Enter report content (each Enter = new line, type END to finish):"
        );
        if (content == null){
            return;
        }

        Report report = new Report(type, title, content);
        reportPage.addReport(report);
    }

    private ReportType pickReportType() {
        ReportType[] types = ReportType.values();
        System.out.println("Select report type:");
        for (int i = 0; i < types.length; i++) {
            System.out.println("  " + (i + 1) + ". " + types[i]);
        }
        System.out.print("Choose: ");
        try {
            int idx = Integer.parseInt(scanner.nextLine().trim());
            if (idx < 1 || idx > types.length) {
                System.out.println("Invalid type number.");
                return null;
            }
            return types[idx - 1];
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number.");
            return null;
        }
    }

    private void viewReportsByType() {
        ReportType type = pickReportType();
        if (type == null){
            return;
        }
        reportPage.printByType(type);
    }

    private void viewSingleReport() {
        reportPage.printAll();
        if (reportPage.getAllReports().isEmpty()){
                return;
            }
        System.out.print("Enter report number to view in full: ");
        try {
            int idx = Integer.parseInt(scanner.nextLine().trim());
            reportPage.printReport(idx);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number.");
        }
    }

    private String readMultiLineText(String prompt) {
        System.out.println(prompt);
        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("END")) break;
            if (sb.length() > 0) sb.append("\n");
            sb.append(line);
        }
        String result = sb.toString().trim();
        if (result.isEmpty()) {
            System.out.println("Content cannot be empty.");
            return null;
        }
        return result;
    }
}
