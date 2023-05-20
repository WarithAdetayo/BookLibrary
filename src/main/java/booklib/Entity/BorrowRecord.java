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
        this.recordID = (this.date + this.borrowedBook.id()).replace(" ", "");
        return this;
    }

    public void markBookReturned() {
        this.returnedDate = new Date();
        this.bookReturned = true;
    }

    public String getRecordId() {
        return this.recordID;
    }

    public boolean bookIsReturned() {
        return  this.bookReturned;
    }

    public Date getReturnedDate() {
        return  this.returnedDate;
    }

    public Book getBorrowedBook() {
        return borrowedBook;
    }

    public Person getBorrower() {
        return borrower;
    }

    public Date getBorrowDate() {
        return this.date;
    }

    public Date getExpectedReturnDate() {
        return this.expectedReturnDate;
    }
}
