package lab5.ticket;

import lab5.InputReader;
import lab5.InputReader.NamedReader;

import java.time.LocalDate;
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

    public static Ticket read(boolean isNew) throws InputReader.CannotReadObjectException {
        List<Object> ticketFields = InputReader.readObject(ticketReader);

        ticketFields.add(readCoordinates());
        ticketFields.add(readPerson());

        if (!(isNew)) ticketFields.addAll(InputReader.readObject(ticketAutoFieldReader));

        return create(ticketFields);
    }

    public static Coordinates readCoordinates() throws InputReader.CannotReadObjectException {
        List<Object> coordinatesFields = InputReader.readObject(coordinatesReader);

        Long x = (Long) coordinatesFields.get(0);
        Integer y = (Integer) coordinatesFields.get(1);

        return new Coordinates(x, y);
    }

    public static Person readPerson() throws InputReader.CannotReadObjectException {
        List<Object> personFields = InputReader.readObject(personReader);

        LocalDate birthday = (LocalDate) personFields.get(0);
        double height = (double) personFields.get(1);
        int weight = (int) personFields.get(2);
        String passportID = (String) personFields.get(3);

        return new Person(birthday, height, weight, passportID);
    }

    public static void update(Ticket ticket) throws InputReader.CannotReadObjectException {
        List<Object> ticketFields = InputReader.readObject(ticketReader);

        ticketFields.add(readCoordinates());
        ticketFields.add(readPerson());

        ticket.setName((String) ticketFields.get(0));
        ticket.setPrice((int) ticketFields.get(1));
        ticket.setType((TicketType) ticketFields.get(2));
        ticket.setCoordinates((Coordinates) ticketFields.get(3));
        ticket.setPerson((Person) ticketFields.get(4));
    }

    private static Ticket create(List<Object> ticketFields) {
        String name = (String) ticketFields.get(0);
        int price = (int) ticketFields.get(1);
        TicketType type = (TicketType) ticketFields.get(2);
        Coordinates coordinates = (Coordinates) ticketFields.get(3);
        Person person = (Person) ticketFields.get(4);

        if (ticketFields.size() == 7) {
            long id = (long) ticketFields.get(5);
            Date creationDate = (Date) ticketFields.get(6);

            return new Ticket(name, price, type, coordinates, person, id, creationDate);
        } else return new Ticket(name, price, type, coordinates, person);
    }
}