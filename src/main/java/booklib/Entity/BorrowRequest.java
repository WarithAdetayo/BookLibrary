package booklib.Entity;

public class BorrowRequest implements Comparable<BorrowRequest> {
    private String title;
    private Person person;
    private int duration;


    @Override
    public int compareTo(BorrowRequest o) {
        if (this.title.compareTo(o.title) != 0) {
            return 0;
        }

        return this.person.compareTo(o.person);
    }

    public BorrowRequest bookTitle(String title) {
        this.title = title;
        return  this;
    }

    public BorrowRequest borrowDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public BorrowRequest borrower(String name, String contact) {
        this.person = new Person(name, contact);
        return this;
    }

    public  BorrowRequest personType(String type) {
        this.person.setPersonnel(type);
        return this;
    }

    public int getDuration() {
        return duration;
    }

    public String getTitle() {
        return title;
    }

    public Person getPerson() {
        return this.person;
    }

    public String getPersonName() {
        return this.person.getName();
    }
}
