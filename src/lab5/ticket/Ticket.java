package lab5.ticket;

import lab5.exceptions.IncorrectFieldException;

import java.util.*;

public class Ticket implements Comparable<Ticket>{
    private static long ticketCount = 0;

    private final long id;
    private final Date creationDate;

    private String name;
    private int price;
    private TicketType type;
    private Coordinates coordinates;
    private Person person;

    public Ticket(List<Object> fields) {
        name = (String) fields.get(0);
        price = (int) fields.get(1);
        type = (TicketType) fields.get(2);
        coordinates = (Coordinates) fields.get(3);
        person = (Person) fields.get(4);
        id =  (fields.size() == 6 ? (long) fields.get(5) : ticketCount++);
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

    public void setFields(List<Object> fields) {
        name = (String) fields.get(0);
        price = (int) fields.get(1);
        type = (TicketType) fields.get(2);
        coordinates = (Coordinates) fields.get(3);
        person = (Person) fields.get(4);
    }

    public static long readId(Scanner scanner) throws IncorrectFieldException {
        String idString = scanner.next().trim();

        try {
            return Long.parseLong(idString);
        } catch (NumberFormatException e) {
            throw new IncorrectFieldException(idString);
        }
    }

    public static Date readCreationDate(Scanner scanner) throws IncorrectFieldException {
        String creationDateString = scanner.next().trim();

        try {
            return new Date(Long.parseLong(creationDateString));
        } catch (NumberFormatException e) {
            throw new IncorrectFieldException(creationDateString);
        }
    }

    public static String readName(Scanner scanner) throws IncorrectFieldException {
        String name = scanner.next().trim();

        if (name.isEmpty()) throw new IncorrectFieldException(name);

        return name;
    }

    public static Integer readPrice(Scanner scanner) throws IncorrectFieldException {
        String priceString = scanner.next().trim();
        int price;

        try {
            price = Integer.parseInt(priceString);
        } catch (NumberFormatException e) {
            throw new IncorrectFieldException(priceString);
        }

        if (price <= 0) throw new IncorrectFieldException(priceString);

        return price;
    }

    public static TicketType readTicketType(Scanner scanner) throws IncorrectFieldException {
        String ticketTypeString = scanner.next().trim();

        try {
            return TicketType.valueOf(ticketTypeString.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IncorrectFieldException(ticketTypeString);
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
    public boolean equals(Object object) {
        if (object == this) return true;
        if (!(object instanceof Ticket)) return false;

        Ticket ticket = (Ticket) object;
        return ticket.id == id;
    }

    @Override
    public int compareTo(Ticket ticket) {
        if (ticket.equals(this)) return 0;

        int result = ticket.creationDate.compareTo(creationDate);
        return result != 0 ? result : Integer.compare(ticket.price, price);
    }
}