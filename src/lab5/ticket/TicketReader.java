package lab5.ticket;

import lab5.InputReader;
import lab5.InputReader.PairReader;

import java.util.*;

public class TicketReader {
    public static List<PairReader> personReader = new ArrayList<>();
    public static List<PairReader> coordinatesReader = new ArrayList<>();
    public static List<PairReader> ticketReader = new ArrayList<>();
    public static List<PairReader> ticketAutoFieldReader = new ArrayList<>();

    static {
        personReader.add(new PairReader("birthday", Person::readBirthday));
        personReader.add(new PairReader("height", Person::readHeight));
        personReader.add(new PairReader("weight", Person::readWeight));
        personReader.add(new PairReader("passport ID", Person::readPassportID));

        coordinatesReader.add(new PairReader("x coordinate", Coordinates::readX));
        coordinatesReader.add(new PairReader("y coordinate", Coordinates::readY));

        ticketReader.add(new PairReader("name", Ticket::readName));
        ticketReader.add(new PairReader("price", Ticket::readPrice));
        ticketReader.add(new PairReader("ticket type", Ticket::readTicketType));

        ticketAutoFieldReader.add(new PairReader("id", Ticket::readId));
        ticketAutoFieldReader.add(new PairReader("creation date", Ticket::readCreationDate));
    }

    public static Ticket readTicket() {
        List<Object> ticketFields = InputReader.readObject(ticketReader);
        ticketFields.add(readCoordinates());
        ticketFields.add(readPerson());
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
