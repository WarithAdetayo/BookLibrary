package booklib.Entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BorrowRequestTest {
    BorrowRequest teacherRequest, seniorRequest, juniorRequest;

    String bookTitle = "TestBookTitle";
    String borrower = "TestBorrower";
    String contact = "43435546";
    int duration = 10;

    @BeforeEach
    void setUp() {

        this.teacherRequest = new BorrowRequest()
                .bookTitle(bookTitle)
                .borrower(borrower, contact)
                .personType("Teacher")
                .borrowDuration(duration);

        this.seniorRequest = new BorrowRequest()
                .bookTitle(bookTitle)
                .borrower("TestBorrowerB", "545667")
                .personType("Senior-Student")
                .borrowDuration(45);

        this.juniorRequest = new BorrowRequest()
                .bookTitle(bookTitle)
                .borrower("TestBorrowerB", "33432")
                .personType("Junior-Student")
                .borrowDuration(20);
    }

    @Test
    void compareTo() {

        assertEquals(-1, teacherRequest.compareTo(seniorRequest));
        assertEquals(-2, teacherRequest.compareTo(juniorRequest));
        assertEquals(-1, seniorRequest.compareTo(juniorRequest));

        assertTrue(teacherRequest.compareTo(seniorRequest) < 0);
        assertTrue(teacherRequest.compareTo(juniorRequest) < 0);
    }

    @Test
    void personType() {
        assertSame(this.teacherRequest.getPerson().getType(), PersonnelType.TEACHER);
        assertSame(this.seniorRequest.getPerson().getType(), PersonnelType.SENIOR_STUDENT);
        assertSame(this.juniorRequest.getPerson().getType(), PersonnelType.JUNIOR_STUDENT);
    }

    @Test
    void getDuration() {
        assertSame(duration, this.teacherRequest.getDuration());
    }

    @Test
    void getTitle() {
        assertSame(bookTitle, this.teacherRequest.getTitle());
    }

    @Test
    void getPerson() {
        assertSame(borrower, teacherRequest.getPerson().getName());
        assertSame(contact, teacherRequest.getPerson().getContact());
    }

    @Test
    void getPersonName() {
        assertSame(borrower, teacherRequest.getPersonName());
    }
}