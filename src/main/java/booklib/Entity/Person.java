package booklib.Entity;

enum PersonnelType {
    TEACHER,
    SENIOR_STUDENT,
    JUNIOR_STUDENT
}

interface Personnel {
    int getPriority();
}

class TeacherPerson implements Personnel {
    PersonnelType type = PersonnelType.TEACHER;

    public  int getPriority() {
        return 3;
    }
}


class SeniorStudentPerson implements Personnel {
    PersonnelType type = PersonnelType.SENIOR_STUDENT;

    public  int getPriority() {
        return 2;
    }
}


class JuniorStudentPerson implements Personnel {
    PersonnelType type = PersonnelType.JUNIOR_STUDENT;

    public  int getPriority() {
        return 1;
    }
}


class Person implements Comparable<Person> {
    private final String name;
    private String contactNumber;
    private Personnel personel;

    public Person(String name, String contactNumber) {
        this.name = name;
        this.contactNumber = contactNumber;
    }

    public Person setPersonnel(String personType) {
        switch (personType.toUpperCase()) {
            case "TEACHER":
                personel = new TeacherPerson();
            case "SENIOR-STUDENT":
                personel = new SeniorStudentPerson();
            default:
                personel = new JuniorStudentPerson();
        }
        return this;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int compareTo(Person o) {
        return this.personel.getPriority() - o.personel.getPriority();
    }

    public String getName() {
        return this.name;
    }
}
