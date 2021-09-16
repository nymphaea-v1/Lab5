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
        personReader.add(new NamedReader("person birthday", Person::readBirthday));
        personReader.add(new NamedReader("person height", Person::readHeight));
        personReader.add(new NamedReader("person weight", Person::readWeight));
        personReader.add(new NamedReader("person passport ID", Person::readPassportID));

        coordinatesReader.add(new NamedReader("x coordinate", Coordinates::readX));
        coordinatesReader.add(new NamedReader("y coordinate", Coordinates::readY));

        ticketReader.add(new NamedReader("ticket name", Ticket::readName));
        ticketReader.add(new NamedReader("ticket price", Ticket::readPrice));
        ticketReader.add(new NamedReader("ticket type " + Arrays.toString(TicketType.values()), Ticket::readTicketType));

        ticketAutoFieldReader.add(new NamedReader("ticket id", Ticket::readId));
        ticketAutoFieldReader.add(new NamedReader("ticket creation date", Ticket::readCreationDate));
    }

    public static Ticket readTicket() {
        return new Ticket(readTicketFields());
    }

    public static Ticket readTicket(Scanner scanner) throws IncorrectFieldException {
        List<Object> ticketFields = InputReader.readObject(ticketReader, scanner);

        ticketFields.add(new Coordinates(InputReader.readObject(coordinatesReader, scanner)));
        ticketFields.add(new Person(InputReader.readObject(personReader, scanner)));
        ticketFields.addAll(InputReader.readObject(ticketAutoFieldReader, scanner));

        return new Ticket(ticketFields);
    }

    public static List<Object> readTicketFields() {
        List<Object> ticketFields = InputReader.readObject(ticketReader);

        ticketFields.add(readCoordinates());
        ticketFields.add(readPerson());

        return ticketFields;
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