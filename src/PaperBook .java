public class PaperBook extends Book {
    public PaperBook(String title, String author, String ISBN) {
        super(title, author, ISBN);
    }

    @Override
    public String getType() {
        return "ورقي";
    }
}
