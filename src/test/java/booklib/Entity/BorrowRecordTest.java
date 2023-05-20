package booklib.Entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BorrowRecordTest {
    BorrowRecord newRecord;
    Person person;
    Book book;
    BookCollection collection;

    @BeforeEach
    void setUp() {
        person = new Person("TestName", "54545")
                .setPersonnel("Senior-Student");

       collection = new BookCollection("Title", "Author");
        collection.addCopy();
        book = collection.takeBookFromShelf();
    }

    @Test
    void enter() {
        newRecord = new BorrowRecord()
                .setBorrower(person)
                .setBorrowedBook(book)
                .setExpectedReturnDate(new Date())
                .enter();

        assertSame(person, newRecord.getBorrower());
        assertNotNull(newRecord.getRecordId());
        assertNull(newRecord.getReturnedDate());
    }

    @Test
    void markBookReturned() {
        newRecord = new BorrowRecord()
                .setBorrower(person)
                .setBorrowedBook(book)
                .setExpectedReturnDate(new Date())
                .enter();

        newRecord.markBookReturned();
        assertTrue(newRecord.bookIsReturned());
        assertNotNull(newRecord.getReturnedDate());
    }
}