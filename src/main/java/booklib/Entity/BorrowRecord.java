package booklib.Entity;

import java.util.Date;

class BorrowRecord {
    private String recordID;
    private Person borrower;
    private Book borrowedBook;
    private Date date;
    private Date returnedDate;
    private Date expectedReturnDate;
    private boolean bookReturned;

    public BorrowRecord setBorrower(Person borrower) {
        this.borrower = borrower;
        return this;
    }

    public BorrowRecord setBorrowedBook(Book book) {
        this.borrowedBook = book;
        return this;
    }

    public BorrowRecord setExpectedReturnDate(Date date) {
        this.expectedReturnDate = date;
        return this;
    }

    public BorrowRecord enter() {
        this.date = new Date();
        this.returnedDate = null;
        this.bookReturned = false;
        this.recordID = this.date.toString() + this.borrowedBook.id();
        return this;
    }

    public void markBookReturned() {
        this.returnedDate = new Date();
        this.bookReturned = true;
    }

    public String getRecordId() {
        return this.recordID;
    }

    public Book getBorrowedBook() {
        return borrowedBook;
    }

    public Person getBorrower() {
        return borrower;
    }
}
