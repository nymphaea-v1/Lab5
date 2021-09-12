package lab5.ticket;

import lab5.exceptions.IncorrectFieldException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Person {
    private final LocalDate birthday;
    private final double height;
    private final int weight;
    private final String passportID;

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getPassportID() {
        return passportID;
    }

    public Person(List<Object> fields) {
        birthday = (LocalDate) fields.get(0);
        height = (double) fields.get(1);
        weight = (int) fields.get(2);
        passportID = (String) fields.get(3);
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
    public boolean equals(Object o) {
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        return birthday.equals(person.getBirthday())
                && (passportID == null || person.passportID == null || passportID.equals(person.getPassportID()));
    }
}