package booklib.Entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {
    Library library = Library.getInstance();

    @Test
    void addToCollection() {
        library.addToCollection("Some Book Title", "Some Author", 10);
        assertTrue(library.isBookAvailable("Some Book Title"));
    }

    @Test
    void testAddToCollection() {
        library.addToCollection("Some Book Title", "Some Author");
        assertFalse(library.isBookAvailable("Some Book Title"));
    }

    @Test
    void isBookAvailable() {
        library.addToCollection("Another Available Book Tile", "Some Author", 1);
        library.addToCollection("Another Unavailable Book TiTle", "Some Author");

        assertTrue(library.isBookAvailable("Another Available Book Tile"));
        assertFalse(library.isBookAvailable("Another Unavailable Book TiTle"));
    }

    @Test
    void borrowBook() {
        library.addToCollection("Some Book To Borrow", "Some Author", 1);
        BorrowRequest request = new BorrowRequest()
                .bookTitle("Some Book To Borrow")
                .borrower("Test Borrower Name", "454464")
                .personType("Teacher")
                .borrowDuration(25);


        BorrowResponse response = library.borrowBook(request);

        assertTrue(response.bookAvailable());


        assertNotNull(response.expectedReturnDate());
        assertNotNull(response.borrowID());

        request = new BorrowRequest()
                .bookTitle("Some Unavailable Book")
                .borrower("Test Borrower Name", "554464")
                .personType("Teacher")
                .borrowDuration(10);
        response = library.borrowBook(request);
        assertFalse(response.bookAvailable());
        assertNull(response.borrowID());
        assertNull(response.expectedReturnDate());
    }

    @Test
    void returnBook() {
        library.addToCollection("Just Another Book Title", "Some Author", 1);
        BorrowRequest request = new BorrowRequest()
                .bookTitle("Just Another Book Title")
                .borrower("Test Borrower Name", "454464")
                .personType("Teacher")
                .borrowDuration(25);


        BorrowResponse response = library.borrowBook(request);
        assertFalse(library.isBookAvailable("Just Another Book Title"));
        library.returnBook(response.borrowID());
        assertTrue(library.isBookAvailable("Just Another Book Title"));

    }
}