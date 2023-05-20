package booklib.Entity;

enum PersonnelType {
    TEACHER,
    SENIOR_STUDENT,
    JUNIOR_STUDENT
}

interface Personnel {
    int getPriority();
    PersonnelType getType();
}

// Priority Set in order of position
// 0 - Highest Rank
// 1 - Mid-Rank
// 2 - Lowest Rank

class TeacherPerson implements Personnel {
    final PersonnelType type = PersonnelType.TEACHER;

    public  int getPriority() {
        return 0;
    }

    public PersonnelType getType() {
        return this.type;
    }
}


class SeniorStudentPerson implements Personnel {
    final PersonnelType type = PersonnelType.SENIOR_STUDENT;

    public  int getPriority() {
        return 1;
    }

    public PersonnelType getType() {
        return this.type;
    }
}


class JuniorStudentPerson implements Personnel {
    final PersonnelType type = PersonnelType.JUNIOR_STUDENT;

    public  int getPriority() {
        return 2;
    }

    public PersonnelType getType() {
        return this.type;
    }
}


class Person implements Comparable<Person> {
    private final String name;
    private final String contactNumber;
    private Personnel personnel;

    public Person(String name, String contactNumber) {
        this.name = name;
        this.contactNumber = contactNumber;
    }

    public Person setPersonnel(String personType) {
        switch (personType.toUpperCase()) {
            case "TEACHER" -> personnel = new TeacherPerson();
            case "SENIOR-STUDENT" -> personnel = new SeniorStudentPerson();
            default -> personnel = new JuniorStudentPerson();
        }
        return this;
    }

    public PersonnelType getType() {
        return this.personnel.getType();
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int compareTo(Person o) {
        return this.personnel.getPriority() - o.personnel.getPriority();
    }

    public String getName() {
        return this.name;
    }

    public String getContact() {
        return this.contactNumber;
    }
}
