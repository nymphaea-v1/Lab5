package lab5.ticket;

import lab5.exceptions.IncorrectFieldException;

import java.util.Arrays;
import java.util.Date;


public class TicketManager {
    public static Ticket createTicket(String[] ticketFields) throws IncorrectFieldException {
        long id = TicketFieldsParser.parseId(ticketFields[0]);
        String name = TicketFieldsParser.parseName(ticketFields[1]);
        Coordinates coordinates = TicketFieldsParser.parseCoordinates(Arrays.copyOfRange(ticketFields, 2, 4));
        Date creationDate = TicketFieldsParser.parseCreationDate(ticketFields[4]);
        int price = TicketFieldsParser.parsePrice(ticketFields[5]);
        TicketType ticketType = TicketFieldsParser.parseType(ticketFields[6]);
        Person person = TicketFieldsParser.parsePerson(Arrays.copyOfRange(ticketFields, 7, 11));

        return new Ticket(id, name, coordinates, creationDate, price, ticketType, person);
    }

    public static String toCSV(Ticket ticket) {
        return ticket.toCSV();
    }
}
