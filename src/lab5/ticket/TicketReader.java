package lab5.ticket;

import lab5.InputReader;
import lab5.Reader;
import lab5.exceptions.IncorrectFieldException;

import java.time.LocalDate;
import java.util.*;

/**
 * Class that contains variations of methods for reading and creating Ticket and its fields instances
 *
 * @see Ticket
 * @see Person
 * @see Coordinates
 * @see InputReader
 */

public class TicketReader {
    private final static List<Reader> personReaders = new ArrayList<>();
    private final static List<Reader> coordinatesReaders = new ArrayList<>();
    private final static List<Reader> ticketBasicReaders = new ArrayList<>();
    private final static List<Reader> ticketExtraReaders = new ArrayList<>();

    static {
        personReaders.add(new Reader("person birthday", Person::readBirthday));
        personReaders.add(new Reader("person height", Person::readHeight));
        personReaders.add(new Reader("person weight", Person::readWeight));
        personReaders.add(new Reader("person passport ID", Person::readPassportID));

        coordinatesReaders.add(new Reader("x coordinate", Coordinates::readX));
        coordinatesReaders.add(new Reader("y coordinate", Coordinates::readY));

        ticketBasicReaders.add(new Reader("ticket name", Ticket::readName));
        ticketBasicReaders.add(new Reader("ticket price", Ticket::readPrice));
        ticketBasicReaders.add(new Reader("ticket type " + Arrays.toString(TicketType.values()), Ticket::readType));

        ticketExtraReaders.add(new Reader("ticket id", Ticket::readId));
        ticketExtraReaders.add(new Reader("ticket creation date", Ticket::readCreationDate));
    }

    /**
     * Reads a list of ticket parameters from a specified iterator and construct a Ticket instance
     *
     * @param iterator a source from where ticket parameters will be read
     * @return Ticket instance
     * @throws IncorrectFieldException - if an incorrect field was encountered
     */

    public static Ticket readTicket(Iterator<String> iterator) throws IncorrectFieldException {
        List<Object> ticketFields = readObjectFields(iterator, ticketBasicReaders);
        ticketFields.add(createCoordinates(readObjectFields(iterator, coordinatesReaders)));
        ticketFields.add(createPerson(readObjectFields(iterator, personReaders)));
        ticketFields.addAll(readObjectFields(iterator, ticketExtraReaders));

        return createTicket(ticketFields);
    }

    private static List<Object> readObjectFields(Iterator<String> iterator, List<Reader> readers) throws IncorrectFieldException {
        List<Object> fields = new ArrayList<>();
        for (Reader reader : readers) {
            if (!(iterator.hasNext())) throw new IncorrectFieldException("not enough fields");
            fields.add(reader.reader.read(iterator));
        }
        return fields;
    }

    /**
     * Reads a list of ticket parameters from a specified input reader and construct a new Ticket instance with the specified id
     *
     * @param inputReader a source from where ticket parameters will be read
     * @param id an ticket id
     * @return Ticket instance
     */

    public static Ticket readNewTicket(InputReader inputReader, long id) {
        List<Object> ticketFields = inputReader.readObject(ticketBasicReaders);

        ticketFields.add(readCoordinates(inputReader));
        ticketFields.add(readPerson(inputReader));

        ticketFields.add(id);
        ticketFields.add(new Date());

        return createTicket(ticketFields);
    }

    /**
     * Reads a list of coordinates parameters from a specified input reader and construct a new Coordinates instance
     *
     * @param inputReader a source from where ticket parameters will be read
     * @return Coordinates instance
     */

    public static Coordinates readCoordinates(InputReader inputReader) {
        List<Object> coordinatesFields = inputReader.readObject(coordinatesReaders);

        return createCoordinates(coordinatesFields);
    }

    /**
     * Reads a list of person parameters from a specified input reader and construct a new Person instance
     *
     * @param inputReader a source from where ticket parameters will be read
     * @return Person instance
     */

    public static Person readPerson(InputReader inputReader) {
        List<Object> personFields = inputReader.readObject(personReaders);

        return createPerson(personFields);
    }

    /**
     * Reads a list of ticket parameters from a specified input reader and updates fields in the specified Ticket instance
     *
     * @param inputReader a source from where ticket parameters will be read
     * @param ticket Ticket instance which fields will be updated
     */

    public static void updateTicket(InputReader inputReader, Ticket ticket) {
        List<Object> ticketFields = inputReader.readObject(ticketBasicReaders);

        ticketFields.add(readCoordinates(inputReader));
        ticketFields.add(readPerson(inputReader));

        ticket.setName((String) ticketFields.get(0));
        ticket.setPrice((int) ticketFields.get(1));
        ticket.setType((TicketType) ticketFields.get(2));
        ticket.setCoordinates((Coordinates) ticketFields.get(3));
        ticket.setPerson((Person) ticketFields.get(4));
    }

    private static Ticket createTicket(List<Object> ticketFields) {
        String name = (String) ticketFields.get(0);
        int price = (int) ticketFields.get(1);
        TicketType type = (TicketType) ticketFields.get(2);
        Coordinates coordinates = (Coordinates) ticketFields.get(3);
        Person person = (Person) ticketFields.get(4);
        long id = (long) ticketFields.get(5);
        Date creationDate = (Date) ticketFields.get(6);

        return new Ticket(name, price, type, coordinates, person, id, creationDate);
    }

    private static Coordinates createCoordinates(List<Object> coordinatesFields) {
        Long x = (Long) coordinatesFields.get(0);
        Integer y = (Integer) coordinatesFields.get(1);

        return new Coordinates(x, y);
    }

    private static Person createPerson(List<Object> personFields) {
        LocalDate birthday = (LocalDate) personFields.get(0);
        double height = (double) personFields.get(1);
        int weight = (int) personFields.get(2);
        String passportID = (String) personFields.get(3);

        return new Person(birthday, height, weight, passportID);
    }
}