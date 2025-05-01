import java.util.ArrayList;

public class LibraryHelper {
    public static Book findBookByISBN(ArrayList<Book> books, String ISBN) {
        for (Book book : books) {
            if (book.getISBN().equalsIgnoreCase(ISBN)) {
                return book;
            }
        }
        return null;
    }

    public static Borrower findBorrowerById(ArrayList<Borrower> borrowers, String id) {
        for (Borrower b : borrowers) {
            if (b.getUniversityId().equalsIgnoreCase(id)) {
                return b;
            }
        }
        return null;
    }
}
