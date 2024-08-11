package Library;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Library {
    private List<Book> books;
    private HashMap<String, List<IssuedBook>> issuedBooks;

    public Library() {
        this.books = new ArrayList<>();
        this.issuedBooks = new HashMap<>();
    }

    public void addBook(String title, String author) {
        this.books.add(new Book(title, author));

    }

    public boolean removeBook(String title, String author) {
        for (Book book : this.books) {
            if (book.title.equalsIgnoreCase(title) && book.author.equalsIgnoreCase(author)) {
                books.remove(book);
                System.out.println("The book: " + title + " by " + author + " has been removed.");
                return true;
            }
        }
        System.out.println("The book is not available for removal.");
        return false;
    }

    /*
      public void removeBook(String title, String author){

          for (int i = 0 ; i < this.books.size() ; i++){
              if (books.get(i).title.equalsIgnoreCase(title) && books.get(i).author.equalsIgnoreCase(author)){
                  books.remove(i);
                  System.out.println("The book: " + title + " by " + author + " has been removed.");

              }
          }
          System.out.println("The book is not available for removal.");
      }
  */
    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
        } else {
            System.out.println("Books in the Library:");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    public boolean searchBook(String title, String author) {
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title) && book.author.equalsIgnoreCase(author)) {
                System.out.println("The book: " + book + " is available.");
                return true;
            }
        }
        System.out.println("The book is not available.");
        return false;
    }

    public boolean issueBook(String title, String studentId) {
        System.out.println("You can issu a book only for 30 days ");
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title)) {
                LocalDate issueDate = LocalDate.now();
                IssuedBook issuedBook = new IssuedBook(studentId, issueDate, title);

                /*
                  Add the issued book to the student's list 🥱.
                */
                issuedBooks.putIfAbsent(studentId, new ArrayList<>());
                issuedBooks.get(studentId).add(issuedBook);

                System.out.println("The book: " + title + " has been issued to student ID: " + studentId + " on " + issueDate);
                return true;
            }
        }
        System.out.println("The book is either not available or already issued.");
        return false;
    }

    public void returnBook(String title, String studentId) {
        List<IssuedBook> studentIssuedBooks = issuedBooks.get(studentId);
        if (studentIssuedBooks != null) {
            for (IssuedBook issuedBook : studentIssuedBooks) {
                if (issuedBook.title.equalsIgnoreCase(title)) {
                    LocalDate returnDate = LocalDate.now();
                    long daysBetween = issuedBook.issueDate.until(returnDate).getDays();
                    studentIssuedBooks.remove(issuedBook);

                    System.out.println("The book: " + title + " has been returned successfully.");

                    if (daysBetween > 30) {
                        long penaltyDays = daysBetween - 30;
                        double penalty = penaltyDays * 5;
                        System.out.println("You have to pay a penalty of Rs " + penalty + " for returning the book " + penaltyDays + " days late.");
                    } else {
                        System.out.println("The book was returned on time.");
                    }
                    return;
                }
            }
        }
        System.out.println("The book was not issued to this student.");
    }

    public void viewIssuedBooks(String studentId) {
        List<IssuedBook> studentIssuedBooks = issuedBooks.get(studentId);
        if (studentIssuedBooks == null || studentIssuedBooks.isEmpty()) {
            System.out.println("No books are currently issued to student ID: " + studentId);
        } else {
            System.out.println("Issued Books for student ID " + studentId + ":");
            for (IssuedBook issuedBook : studentIssuedBooks) {
                System.out.println("Book: " + issuedBook.title + ", Issued on: " + issuedBook.issueDate);
            }
        }
    }
}