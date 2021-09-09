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

    public Person(String[] fields) throws IncorrectFieldException {
        if (fields.length != 4) throw new IncorrectFieldException("person fields size");

        try {
            birthday = LocalDate.parse(fields[0]);
            height = Double.parseDouble(fields[1]);
            weight = Integer.parseInt(fields[2]);
            if ((passportID = fields[3]).length() < 10) throw new IncorrectFieldException("person " + passportID);
        } catch (DateTimeParseException | NumberFormatException e) {
            throw new IncorrectFieldException("Person parse: " + e.getMessage());
        }
    }

    public Person(List<Object> fields) {
        birthday = (LocalDate) fields.get(0);
        height = (double) fields.get(1);
        weight = (int) fields.get(2);
        passportID = (String) fields.get(3);
    }

    public Person(LocalDate birthday, double height, int weight, String passportID) {
        this.birthday = birthday;
        this.height = height;
        this.weight = weight;
        this.passportID = passportID;
    }

    public static LocalDate readBirthday(Scanner scanner) throws IncorrectFieldException {
        String birthdayString = scanner.next();

        try {
            return LocalDate.parse(birthdayString);
        } catch (DateTimeParseException e) {
            throw new IncorrectFieldException(birthdayString);
        }
    }

    public static double readHeight(Scanner scanner) throws IncorrectFieldException {
        String heightString = scanner.next();
        double height;

        try {
            height = Double.parseDouble(heightString);
        } catch (NumberFormatException e) {
            throw new IncorrectFieldException("height");
        }

        if (height > 0) return height;

        throw new IncorrectFieldException(heightString);
    }

    public static int readWeight(Scanner scanner) throws IncorrectFieldException {
        String weightString = scanner.next();
        int weight;

        try {
            weight = Integer.parseInt(weightString);
        } catch (NumberFormatException e) {
            throw new IncorrectFieldException("weight");
        }

        if (weight > 0) return weight;

        throw new IncorrectFieldException(weightString);
    }

    public static String readPassportID(Scanner scanner) throws IncorrectFieldException {
        String passportID = scanner.next();

        if (passportID.matches(" *")) return null;
        if (passportID.length() >= 10) return passportID;

        throw new IncorrectFieldException(passportID);
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