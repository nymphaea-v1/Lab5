package lab5.ticket;

import lab5.exceptions.IncorrectFieldException;

import java.util.*;

public class Ticket implements Comparable<Ticket>{
    private final long id;
    private final String name;
    private final Coordinates coordinates;
    private final Date creationDate;
    private final int price;
    private final TicketType type;
    private final Person person;

    public Ticket(List<Object> fields) {
        name = (String) fields.get(0);
        price = (int) fields.get(1);
        type = (TicketType) fields.get(2);
        coordinates = (Coordinates) fields.get(3);
        person = (Person) fields.get(4);
        id =  (fields.size() == 6 ? (long) fields.get(5) : Math.round(Math.random() * 1000000));
        creationDate = fields.size() == 7 ? (Date) fields.get(6) : new Date();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public TicketType getType() {
        return type;
    }

    public Person getPerson() {
        return person;
    }

    public static long readId(Scanner scanner) throws IncorrectFieldException {
        String idString = scanner.next();

        try {
            return Long.parseLong(idString);
        } catch (NumberFormatException e) {
            throw new IncorrectFieldException("id");
        }
    }

    public static Date readCreationDate(Scanner scanner) throws IncorrectFieldException {
        String creationDateString = scanner.next();

        try {
            return new Date(Long.parseLong(creationDateString));
        } catch (NumberFormatException e) {
            throw new IncorrectFieldException("creation date");
        }
    }

    public static String readName(Scanner scanner) throws IncorrectFieldException {
        String name = scanner.next();

        if (name.trim().isEmpty()) throw new IncorrectFieldException(name);

        return name;
    }

    public static Integer readPrice(Scanner scanner) throws IncorrectFieldException {
        String priceString = scanner.next();
        int price;

        try {
            price = Integer.parseInt(priceString);
        } catch (NumberFormatException e) {
            throw new IncorrectFieldException("price: ");
        }

        if (price > 0) return price;

        throw new IncorrectFieldException(priceString);
    }

    public static TicketType readTicketType(Scanner scanner) throws IncorrectFieldException {
        try {
            return TicketType.valueOf(scanner.next().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IncorrectFieldException("ticket type");
        }
    }

    public String toCSV() {
        return name + ", " + price + ", " + type + ", "  + coordinates + ", " + person.toCSV() + ", " + id + ", " + creationDate.getTime();
    }

    @Override
    public String toString() {
        return "Ticket{ " +
            "id: " + id +
            ", name: " + name +
            ", coordinates: " + coordinates.getX() + ";" + coordinates.getY() +
            ", creationDate: " + creationDate +
            ", price: " + price +
            ", type: " + type +
            ", person: " + person.toString() + " }";
    }

    @Override
    public int compareTo(Ticket ticket) {
        return name.compareTo(ticket.getName());
    }
}