import java.util.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<Borrower> borrowers = new ArrayList<>();
        ArrayList<BorrowingProcess> borrowings = new ArrayList<>();

        while (true) {
            System.out.println("\n--- نظام إدارة المكتبة ---");
            System.out.println(" ** مرحبا بك في رحاب المعرفة بين صفحات العلم و المتعة ** ");
            System.out.println("1. إضافة كتاب");
            System.out.println("2. إضافة مستعير");
            System.out.println("3. إعارة كتاب");
            System.out.println("4. استرجاع كتاب");
            System.out.println("5. عرض الكتب المستعارة لمستعير");
            System.out.println("6. خروج");
            System.out.print("اختيارك: ");

            int choice = input.nextInt();
            input.nextLine(); // تنظيف

            switch (choice) {
                case 1:
                    System.out.print("العنوان: ");
                    String title = input.nextLine();
                    System.out.print("المؤلف: ");
                    String author = input.nextLine();
                    System.out.print("ISBN: ");
                    String isbn = input.nextLine();
                    System.out.print("نوع الكتاب (1-ورقي، 2-الكتروني): ");
                    int type = input.nextInt();
                    input.nextLine();

                    Book newBook = (type == 1) ? new PaperBook(title, author, isbn) : new EBook(title, author, isbn);
                    books.add(newBook);
                    System.out.println("تمت إضافة الكتاب.");
                    break;

                case 2:
                    System.out.print("اسم المستعير: ");
                    String name = input.nextLine();
                    System.out.print("الرقم الجامعي: ");
                    String id = input.nextLine();
                    borrowers.add(new Borrower(name, id));
                    System.out.println("تمت إضافة المستعير.");
                    break;

                case 3:
                    System.out.print("أدخل ISBN الكتاب: ");
                    String borrowISBN = input.nextLine();
                    Book bookToBorrow = LibraryHelper.findBookByISBN(books, borrowISBN);
                    if (bookToBorrow == null || !bookToBorrow.isAvailable()) {
                        System.out.println("الكتاب غير متوفر.");
                        break;
                    }
                    System.out.print("أدخل ID المستعير: ");
                    String borrowerId = input.nextLine();
                    Borrower borrower = LibraryHelper.findBorrowerById(borrowers, borrowerId);
                    if (borrower == null) {
                        System.out.println("المستعير غير موجود.");
                        break;
                    }

                    borrower.borrowBook(bookToBorrow);
                    bookToBorrow.setAvailable(false);
                    borrowings.add(new BorrowingProcess(bookToBorrow, borrower, LocalDate.now(), LocalDate.now().plusDays(14)));
                    System.out.println("تمت الإعارة بنجاح.");
                    break;

                case 4:
                    System.out.print("أدخل ISBN الكتاب المسترجع: ");
                    String returnISBN = input.nextLine();
                    Book bookToReturn = LibraryHelper.findBookByISBN(books, returnISBN);
                    if (bookToReturn == null || bookToReturn.isAvailable()) {
                        System.out.println("الكتاب غير معار.");
                        break;
                    }

                    for (Borrower b : borrowers) {
                        if (b.getBorrowedBooks().contains(bookToReturn)) {
                            b.returnBook(bookToReturn);
                            bookToReturn.setAvailable(true);
                            System.out.println("تم استرجاع الكتاب.");
                            break;
                        }
                    }
                    break;
                    case 5:
                    System.out.print("أدخل ID المستعير: ");
                    String idSearch = input.nextLine();
                    Borrower b = LibraryHelper.findBorrowerById(borrowers, idSearch);
                    if (b == null) {
                        System.out.println("المستعير غير موجود.");
                    } else {
                        for (Book bk : b.getBorrowedBooks()) {
                            System.out.println(bk);
                        }
                    }
                    break;

                case 6:
                System.out.println("**!شكرا لزيارتك لمكتبتنا الى لقاء قريب في رحاب المعرفة**");
                    return;

                default:
                    System.out.println("خيار غير صحيح.");
            }
        }
    }
}
