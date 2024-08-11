package Library;

import java.time.LocalDate;

public class IssuedBook {
    String title;
    String studentId;
    LocalDate issueDate;

    public IssuedBook(String studentId, LocalDate issueDate, String title) {
        this.studentId = studentId;
        this.issueDate = issueDate;
        this.title = title;
    }
}