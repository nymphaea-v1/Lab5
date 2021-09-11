package lab5.ticket;

import lab5.InputReader;
import lab5.InputReader.NamedReader;
import lab5.exceptions.IncorrectFieldException;

import java.util.*;

public class TicketReader {
    public static List<NamedReader> personReader = new ArrayList<>();
    public static List<NamedReader> coordinatesReader = new ArrayList<>();
    public static List<NamedReader> ticketReader = new ArrayList<>();
    public static List<NamedReader> ticketAutoFieldReader = new ArrayList<>();

    static {
        personReader.add(new NamedReader("birthday", Person::readBirthday));
        personReader.add(new NamedReader("height", Person::readHeight));
        personReader.add(new NamedReader("weight", Person::readWeight));
        personReader.add(new NamedReader("passport ID", Person::readPassportID));

        coordinatesReader.add(new NamedReader("x coordinate", Coordinates::readX));
        coordinatesReader.add(new NamedReader("y coordinate", Coordinates::readY));

        ticketReader.add(new NamedReader("name", Ticket::readName));
        ticketReader.add(new NamedReader("price", Ticket::readPrice));
        ticketReader.add(new NamedReader("ticket type", Ticket::readTicketType));

        ticketAutoFieldReader.add(new NamedReader("id", Ticket::readId));
        ticketAutoFieldReader.add(new NamedReader("creation date", Ticket::readCreationDate));
    }

    public static Ticket readTicket() {
        List<Object> ticketFields = InputReader.readObject(ticketReader);

        ticketFields.add(readCoordinates());
        ticketFields.add(readPerson());

        return new Ticket(ticketFields);
    }

    public static Ticket readTicket(Scanner scanner) throws IncorrectFieldException {
        List<Object> ticketFields = InputReader.readObject(ticketReader, scanner);

        ticketFields.add(new Coordinates(InputReader.readObject(coordinatesReader, scanner)));
        ticketFields.add(new Person(InputReader.readObject(personReader, scanner)));
        ticketFields.addAll(InputReader.readObject(ticketAutoFieldReader, scanner));

        return new Ticket(ticketFields);
    }

    public static Coordinates readCoordinates() {
        return new Coordinates(InputReader.readObject(coordinatesReader));
    }

    public static Person readPerson() {
        return new Person(InputReader.readObject(personReader));
    }

    public static String toCSV(Ticket ticket) {
        return ticket.toCSV();
    }
}