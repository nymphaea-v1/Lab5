package lab5.ticket;

import lab5.CollectionManager;
import lab5.console.ConsoleManager;
import lab5.exceptions.CancelCommandException;
import lab5.exceptions.UnreadableInputException;
import lab5.exceptions.IncorrectFieldException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class TicketManager {
    private static Long idCount = 0L;

    public static Ticket createTicket(String[] ticketFields) {
        Long id = Long.valueOf(ticketFields[0]);
        String name = ticketFields[1];

        Long x = Long.valueOf(ticketFields[2]);
        Integer y = Integer.valueOf(ticketFields[3]);
        Coordinates coordinates = new Coordinates(x, y);

        Date creationDate = new Date(Long.parseLong(ticketFields[4]));
        int price = Integer.parseInt(ticketFields[5]);
        TicketType ticketType = TicketType.valueOf(ticketFields[6]);

        LocalDate birthday = LocalDate.parse(ticketFields[7]);
        double height = Double.parseDouble(ticketFields[8]);
        int weight = Integer.parseInt(ticketFields[9]);
        String passportID = ticketFields[10];
        Person person = new Person(birthday, height, weight, passportID);

        return new Ticket(id, name, coordinates, creationDate, price, ticketType, person);
    }

    private interface FieldChecker {
        boolean check(String field);
    }

    private static final LinkedHashMap<String,FieldChecker> ticketFieldCheckers = new LinkedHashMap<>();
    static {
        ticketFieldCheckers.put("id", n -> Long.parseLong(n) > 0 && !CollectionManager.containsId(Long.parseLong(n)));
        ticketFieldCheckers.put("name", null);
        ticketFieldCheckers.put("x coordinate", n -> Long.parseLong(n) <= 565 || Long.parseLong(n) > 0);
        ticketFieldCheckers.put("y coordinate", n -> Integer.parseInt(n) <= 907 || Integer.parseInt(n) > 0);
        ticketFieldCheckers.put("creation date", n -> Long.parseLong(n) < System.currentTimeMillis());
        ticketFieldCheckers.put("price", n -> Integer.parseInt(n) > 0);
        ticketFieldCheckers.put("ticket type", n -> Arrays.toString(TicketType.values()).contains(n) && n.matches("[^(\\W|\\d)]+"));
        ticketFieldCheckers.put("person birthday (YYYY-MM-DD)", n -> LocalDate.parse(n) != null);
        ticketFieldCheckers.put("person height", n -> Double.parseDouble(n) > 0);
        ticketFieldCheckers.put("person weight", n -> Integer.parseInt(n) > 0);
        ticketFieldCheckers.put("person passport ID", (n) -> n.length() >= 10);
    }

    public static String[] readTicketFields() throws CancelCommandException {
        String[] ticketFields = new String[11];

        int fieldCount = -1;
        for (Map.Entry<String, FieldChecker> entry : ticketFieldCheckers.entrySet()) {
            if (++fieldCount == 0 || fieldCount == 4) continue;

            ticketFields[fieldCount] = readField(entry.getKey(), entry.getValue());
        }

        ticketFields[0] = String.valueOf(++idCount);
        ticketFields[4] = String.valueOf(System.currentTimeMillis());

        return ticketFields;
    }

    public static void checkTicketFields(String[] ticketFields) throws IncorrectFieldException {
        if (ticketFields.length != 11) throw new IncorrectFieldException("?");

        int fieldCount = 0;
        for (FieldChecker checker : ticketFieldCheckers.values()) checkField(ticketFields[fieldCount++], checker);
    }

    private static String readField(String target, FieldChecker checker) throws CancelCommandException {
        boolean wasRead = false;
        String field = null;

        System.out.printf("Enter %s: ", target);

        while (!wasRead) {
            try {
                field = ConsoleManager.read();

                if (field == null) throw new UnreadableInputException("empty field");
                if (field.equals("-1")) throw new CancelCommandException();

                try {
                    checkField(field, checker);
                } catch (IncorrectFieldException e) {
                    throw new UnreadableInputException(e.getMessage());
                }

                wasRead = true;
            } catch (UnreadableInputException e) {
                System.out.println(e.getMessage());
            }
        }

        return field;
    }

    private static void checkField(String field, FieldChecker checker) throws IncorrectFieldException {
        if (checkEmptyField(field)) throw new IncorrectFieldException("empty field");

        try {
            if (checker != null && !checker.check(field)) throw new IncorrectFieldException();
        } catch (NumberFormatException e) {
            throw new IncorrectFieldException("not a valid number");
        } catch (DateTimeParseException e) {
            throw new IncorrectFieldException("not a valid date");
        }
    }

    private static boolean checkEmptyField(String field) {
        return field == null || field.equals("");
    }

    public static String toCSV(Ticket ticket) {
        return ticket.toCSV();
    }
}
