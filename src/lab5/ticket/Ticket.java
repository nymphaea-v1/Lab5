package lab5.ticket;

import java.util.Date;

public class Ticket implements Comparable<Ticket> {
    private final long id;
    private final String name;
    private final Coordinates coordinates;
    private final Date creationDate;
    private final int price;
    private final TicketType type;
    private final Person person;

    protected Ticket(Long id, String name, Coordinates coordinates, Date creationDate, int price, TicketType type, Person person) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.price = price;
        this.type = type;
        this.person = person;
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

    @Override
    public String toString() {
        return "Ticket{ " +
                "id: " + id +
                ", name: " + name +
                ", coordinates: " + coordinates.getX() + ";" + coordinates.getY() +
                ", creationDate: " + creationDate +
                ", price: " + price +
                ", type: " + type +
                ", person: " + person.toString() + "}";
    }

    public String toCSV() {
        return id + ", " + name + ", " + coordinates + ", " + creationDate.getTime() + ", " + price + ", " + type + ", " + person.toCSV();
    }

    @Override
    public int compareTo(Ticket ticket) {
        return name.compareTo(ticket.getName());
    }
}