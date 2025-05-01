import java.util.ArrayList;

public class Borrower {
    private String name;
    private String universityId;
    private ArrayList<Book> borrowedBooks = new ArrayList<>();

    public Borrower(String name, String universityId) {
        this.name = name;
        this.universityId = universityId;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    public String getName() {
        return name;
    }

    public String getUniversityId() {
        return universityId;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public String toString() {
        return name + " (ID: " + universityId + ")";
    }
}
