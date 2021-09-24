package lab5.ticket;

import lab5.InputReader;
import lab5.Reader;
import lab5.exceptions.IncorrectFieldException;

import java.time.LocalDate;
import java.util.*;

public class TicketReader {
    private final static List<Reader> personReaders = new ArrayList<>();
    private final static List<Reader> coordinatesReaders = new ArrayList<>();
    private final static List<Reader> ticketBasicReaders = new ArrayList<>();
    private final static List<Reader> ticketExtraReaders = new ArrayList<>();

    private long idCounter;

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

    public TicketReader(long initialId) {
        idCounter = initialId;
    }

    public Ticket readTicket(Scanner scanner) throws IncorrectFieldException {
        List<Object> ticketFields = readObjectFields(scanner, ticketBasicReaders);
        ticketFields.add(createCoordinates(readObjectFields(scanner, coordinatesReaders)));
        ticketFields.add(createPerson(readObjectFields(scanner, personReaders)));
        ticketFields.addAll(readObjectFields(scanner, ticketExtraReaders));

        return createTicket(ticketFields);
    }

    private List<Object> readObjectFields(Scanner scanner, List<Reader> readers) throws IncorrectFieldException {
        List<Object> fields = new ArrayList<>();
        for (Reader reader : readers) {
            if (!(scanner.hasNext())) throw new IncorrectFieldException("not enough fields");
            fields.add(reader.reader.read(scanner));
        }
        return fields;
    }

    public Ticket readNewTicket(InputReader inputReader) {
        List<Object> ticketFields = inputReader.readObject(ticketBasicReaders);

        ticketFields.add(readCoordinates(inputReader));
        ticketFields.add(readPerson(inputReader));

        ticketFields.add(idCounter++);
        ticketFields.add(new Date());

        return createTicket(ticketFields);
    }

    public Coordinates readCoordinates(InputReader inputReader) {
        List<Object> coordinatesFields = inputReader.readObject(coordinatesReaders);

        return createCoordinates(coordinatesFields);
    }

    public Person readPerson(InputReader inputReader) {
        List<Object> personFields = inputReader.readObject(personReaders);

        return createPerson(personFields);
    }

    public void updateTicket(InputReader inputReader, Ticket ticket) {
        List<Object> ticketFields = inputReader.readObject(ticketBasicReaders);

        ticketFields.add(readCoordinates(inputReader));
        ticketFields.add(readPerson(inputReader));

        ticket.setName((String) ticketFields.get(0));
        ticket.setPrice((int) ticketFields.get(1));
        ticket.setType((TicketType) ticketFields.get(2));
        ticket.setCoordinates((Coordinates) ticketFields.get(3));
        ticket.setPerson((Person) ticketFields.get(4));
    }

    private Ticket createTicket(List<Object> ticketFields) {
        String name = (String) ticketFields.get(0);
        int price = (int) ticketFields.get(1);
        TicketType type = (TicketType) ticketFields.get(2);
        Coordinates coordinates = (Coordinates) ticketFields.get(3);
        Person person = (Person) ticketFields.get(4);
        long id = (long) ticketFields.get(5);
        Date creationDate = (Date) ticketFields.get(6);

        if (id >= idCounter) idCounter = id + 1;

        return new Ticket(name, price, type, coordinates, person, id, creationDate);
    }

    private Coordinates createCoordinates(List<Object> coordinatesFields) {
        Long x = (Long) coordinatesFields.get(0);
        Integer y = (Integer) coordinatesFields.get(1);

        return new Coordinates(x, y);
    }

    private Person createPerson(List<Object> personFields) {
        LocalDate birthday = (LocalDate) personFields.get(0);
        double height = (double) personFields.get(1);
        int weight = (int) personFields.get(2);
        String passportID = (String) personFields.get(3);

        return new Person(birthday, height, weight, passportID);
    }
}