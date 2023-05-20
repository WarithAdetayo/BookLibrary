package booklib.Entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookCollectionTest {
    BookCollection collection;


    @Test
    void getAttrib() {
        collection = new BookCollection("TestBookTitle", "TestAuthor");
        assertSame("TestBookTitle", collection.getTitle());
        assertSame("TestAuthor", collection.getAuthor());
    }

    @Test
    void addCopyAndHasAvailableCopy() {
        collection = new BookCollection("TestBookTitle", "TestAuthor");
        collection.addCopy();
        assertTrue(collection.hasAvailableCopies());
    }

    @Test
    void getNumberOfCopies() {
        collection = new BookCollection("New Title", "Some Author");
        collection.addCopy();
        collection.addCopy();

        assertEquals(2, collection.getNumberOfCopies());
        collection.takeBookFromShelf();
        assertEquals(2, collection.getNumberOfCopies());
    }

    @Test
    void getNumberOfAvailableCopies() {
        collection = new BookCollection("New Title", "Some Author");
        collection.addCopy();
        collection.addCopy();

        assertEquals(2, collection.getNumberOfAvailableCopies());
        collection.takeBookFromShelf();
        assertEquals(1, collection.getNumberOfAvailableCopies());
    }


    @Test
    void takeBookFromShelf() {
        collection = new BookCollection("New Title", "Some Author");
        collection.addCopy();
        collection.addCopy();

        collection.takeBookFromShelf();

        assertEquals(1, collection.getNumberOfAvailableCopies());
    }

    @Test
    void returnBookToShelf() {
        collection = new BookCollection("New Title", "Some Author");
        collection.addCopy();
        collection.addCopy();

        Book book = collection.takeBookFromShelf();

        assertEquals(1, collection.getNumberOfAvailableCopies());

        collection.returnBookToShelf(book.id());
        assertEquals(2, collection.getNumberOfAvailableCopies());

    }
}
