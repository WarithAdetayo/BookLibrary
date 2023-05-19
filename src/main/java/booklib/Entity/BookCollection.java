package booklib.Entity;

import java.util.HashMap;
import java.util.Stack;
import java.util.UUID;
import org.apache.log4j.Logger;

record Book(String id, BookCollection collection) {}

class BookCollection {
    private final String title;
    private final String author;

    private final Stack<Book> availableCopies = new Stack<Book>();
    private final HashMap<String, Book> borrowedCopies = new HashMap<String, Book>();

    static Logger log = Logger.getLogger(BookCollection.class.getName());


    public BookCollection(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }
    public void addCopy() {
        UUID id = UUID.randomUUID();
        Book book = new Book(id.toString(), this);
        this.availableCopies.push(book);
    }
    public int getNumberOfCopies() {
        return this.availableCopies.size() + this.borrowedCopies.size();
    }

    public int getNumberOfAvailableCopies() {
        return this.availableCopies.size();
    }

    public boolean hasAvailableCopies() {
        return !this.availableCopies.isEmpty();
    }

    public Book takeBookFromShelf() {
        if(this.hasAvailableCopies()) {
            Book book = availableCopies.pop();
            this.borrowedCopies.put(book.id(), book);
            System.out.println("A Copy burrowed from shelf");
            log.info("A Copy burrowed from shelf");
            return book;
        }
        System.out.println("Out of Copies");
        log.warn("Out of Copies");
        return null;
    }

    public void returnBookToShelf(String bookId) {
        if (this.borrowedCopies.containsKey(bookId)) {
            Book book = this.borrowedCopies.get(bookId);
            this.availableCopies.push(book);
            this.borrowedCopies.remove(bookId);
            System.out.println("Book returned to shelf");
            log.info("Book returned to shelf");
        } else {
            System.out.print("Book with this id never marked borrowed or id is unknown");
            log.warn("Book with this id never marked borrowed or id is unknown");
        }
    }
}
