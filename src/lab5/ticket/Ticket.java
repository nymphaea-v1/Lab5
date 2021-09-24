package lab5.ticket;

import lab5.exceptions.IncorrectFieldException;

import java.util.*;

public class Ticket implements Comparable<Ticket>{
    private final long id;
    private final Date creationDate;

    private String name;
    private int price;
    private TicketType type;
    private Coordinates coordinates;
    private Person person;

    public Ticket(String name, int price, TicketType type, Coordinates coordinates, Person person, long id, Date creationDate) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.coordinates = coordinates;
        this.person = person;
        this.id = id;
        this.creationDate = creationDate;
    }

    public String getName() {
        return name;
    }

    public TicketType getType() {
        return type;
    }

    public Person getPerson() {
        return person;
    }

    public long getId() {
        return id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setType(TicketType type) {
        this.type = type;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setPerson(Person person) {
        this.person = person;
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

    public static TicketType readType(Scanner scanner) throws IncorrectFieldException {
        String typeString = scanner.next().trim();

        try {
            return TicketType.valueOf(typeString.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IncorrectFieldException(typeString);
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

        int result = ticket.type.compareTo(type);
        if (result != 0) return result;

        result = Integer.compare(ticket.price, price);
        if (result != 0) return result;

        return ticket.name.compareTo(name);
    }
}