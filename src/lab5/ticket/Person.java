package lab5.ticket;

import lab5.exceptions.IncorrectFieldException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;

public class Person {
    private final LocalDate birthday;
    private final double height;
    private final int weight;
    private final String passportID;

    public Person(LocalDate birthday, double height, int weight, String passportID) {
        this.birthday = birthday;
        this.height = height;
        this.weight = weight;
        this.passportID = passportID;
    }

    public String getPassportID() {
        return passportID;
    }

    public static LocalDate readBirthday(Scanner scanner) throws IncorrectFieldException {
        String birthdayString = scanner.next().trim();

        try {
            return LocalDate.parse(birthdayString);
        } catch (DateTimeParseException e) {
            throw new IncorrectFieldException(birthdayString);
        }
    }

    public static double readHeight(Scanner scanner) throws IncorrectFieldException {
        String heightString = scanner.next().trim();
        double height;

        try {
            height = Double.parseDouble(heightString);
        } catch (NumberFormatException e) {
            throw new IncorrectFieldException(heightString);
        }

        if (height <= 0) throw new IncorrectFieldException(heightString);

        return height;
    }

    public static int readWeight(Scanner scanner) throws IncorrectFieldException {
        String weightString = scanner.next().trim();
        int weight;

        try {
            weight = Integer.parseInt(weightString);
        } catch (NumberFormatException e) {
            throw new IncorrectFieldException(weightString);
        }

        if (weight <= 0) throw new IncorrectFieldException(weightString);

        return weight;
    }

    public static String readPassportID(Scanner scanner) throws IncorrectFieldException {
        String passportID = scanner.next().trim();

        if (passportID.length() == 0 || passportID.equals("null")) return null;
        if (passportID.length() < 10) throw new IncorrectFieldException(passportID);

        return passportID;
    }

    public String toCSV() {
        return birthday + ", " + height + ", " + weight + ", " + passportID;
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
    public boolean equals(Object object) {
        if (object == this) return true;
        if (!(object instanceof Person)) return false;

        Person person = (Person) object;

        boolean equalPassportID = passportID != null && passportID.equals(person.getPassportID());
        return equalPassportID || (birthday.equals(person.birthday) && (height == person.height) && (weight == person.weight));
    }

    @Override
    public int hashCode() {
        return Objects.hash(birthday, height, weight, passportID);
    }
}