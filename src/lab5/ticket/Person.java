package lab5.ticket;

import java.time.LocalDate;

public class Person implements CSVConvertible {
    private final LocalDate birthday;
    private final double height;
    private final int weight;
    private final String passportID;

    public String getPassportID() {
        return passportID;
    }

    protected Person(LocalDate birthday, double height, int weight, String passportID) {
        this.birthday = birthday;
        this.height = height;
        this.weight = weight;
        this.passportID = passportID;
    }

    @Override
    public String toString() {
        return "Person{ " +
                "birthday: " + birthday + ", " +
                "height: " + height + ", " +
                "weight: " + weight + ", " +
                "passportID: " + passportID + "}";
    }

    @Override
    public String toCSV() {
        return birthday + ", " + height + ", " + weight + ", " + passportID;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Person)) return false;
        else return passportID.equals(((Person) o).getPassportID());
    }
}