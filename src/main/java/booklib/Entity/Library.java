package booklib.Entity;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import org.apache.log4j.Logger;


public class Library {
    private static Library library = null;
    private final HashMap<String, BookCollection> bookCollections =
        new HashMap<>();
    private final HashMap<String, BorrowRecord> borrowRecords =
        new HashMap<>();
    static Logger logger = Logger.getLogger(Library.class.getName());

    private Library(){}

    public static Library getInstance() {
        if (library == null) {
            library = new Library();
        }
        return  library;
    }

    public void addToCollection(String title, String author) {
        if (bookCollections.containsKey(title)) {
            System.out.println("Book already collection");
            logger.warn("Book already collection");
        } else {
            BookCollection collection = new BookCollection(title, author);
            bookCollections.put(title, collection);

            String msg = String.format(
                    "New Collection added\n\tTitle: %s\n\tAuthor%s",
                    title, author
            );
            System.out.println(msg);
            logger.info(msg);
        }
    }

    public boolean isBookAvailable(String title) {
        if (this.bookCollections.containsKey(title)) {
            return this.bookCollections.get(title).hasAvailableCopies();
        }
        return false;
    }

    private String addToRecord(Book book, Person borrower, Date returnDate) {
        BorrowRecord newRecord = new BorrowRecord()
                .setBorrower(borrower)
                .setBorrowedBook(book)
                .setExpectedReturnDate(returnDate)
                .enter();
        this.borrowRecords.put(newRecord.getRecordId(), newRecord);

        return newRecord.getRecordId();
    }

    public BorrowResponse borrowBook(String title, Person borrower, int borrowDuration) {
        if (this.isBookAvailable(title)) {
            Book book = this.bookCollections.get(title).takeBookFromShelf();

            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, borrowDuration);
            Date rDate = c.getTime();


            String recordId = this.addToRecord(book, borrower, rDate);

            return new BorrowResponse(
            true, recordId, rDate
            );

        } else {
            System.out.println("Book requested for not available");
            logger.info("Book requested for not available");
            return new BorrowResponse(false, null, null);
        }
    }

    public BorrowResponse borrowBook(BorrowRequest request) {

        return this.borrowBook(
                request.getTitle(),
                request.getPerson(),
                request.getDuration()
        );
    }

    public void returnBook(String borrowId) {
        if (this.borrowRecords.containsKey(borrowId)) {
            BorrowRecord record = this.borrowRecords.get(borrowId);
            record.getBorrowedBook()
                    .collection()
                    .returnBookToShelf(record.getBorrowedBook().id());
            record.markBookReturned();
            logger.info("Record updated: Borrowed book return by " + record.getBorrower());
        } else {
            logger.warn("Record Not found for the ID, But we will take book from you!!!");
        }
    }

}
