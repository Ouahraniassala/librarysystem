
public class EBook extends Book {
    public EBook(String title, String author, String ISBN) {
        super(title, author, ISBN);
    }

    @Override
    public String getType() {
        return "إلكتروني";
    }
}
