package lab5.ticket;

import lab5.CollectionManager;
import lab5.console.InputManager;
import lab5.exceptions.CancelCommandException;
import lab5.exceptions.IncorrectArgumentException;
import lab5.exceptions.IncorrectFieldException;
import lab5.exceptions.IncorrectScriptException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

// TicketBuilder?
public class TicketManager {
    public static Ticket createTicket(String[] ticketFields) {
        String idString = ticketFields[0];
        long id = idString == null ? (long) (Math.random() * 1000000000) : Long.parseLong(idString);

        String name = ticketFields[1];

        Long x = Long.valueOf(ticketFields[2]);
        Integer y = Integer.valueOf(ticketFields[3]);
        Coordinates coordinates = new Coordinates(x, y);

        String creationDateString = ticketFields[4];
        Date creationDate = new Date(creationDateString == null ? System.currentTimeMillis() : Long.parseLong(creationDateString));

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

    private static final LinkedHashMap<String, FieldChecker> ticketFieldCheckers = new LinkedHashMap<>();
    static {
        ticketFieldCheckers.put("id", n -> Long.parseLong(n) > 0 && !CollectionManager.getIdSet().contains(Long.parseLong(n)));
        ticketFieldCheckers.put("name", null);
        ticketFieldCheckers.put("x coordinate", n -> Long.parseLong(n) <= 565 || Long.parseLong(n) > 0);
        ticketFieldCheckers.put("y coordinate", n -> Integer.parseInt(n) <= 907 || Integer.parseInt(n) > 0);
        ticketFieldCheckers.put("creation date", n -> Long.parseLong(n) < System.currentTimeMillis());
        ticketFieldCheckers.put("price", n -> Integer.parseInt(n) > 0);
        ticketFieldCheckers.put("ticket type", n -> Arrays.stream(TicketType.values()).anyMatch(v -> v.toString().equals(n)));
        ticketFieldCheckers.put("person birthday (YYYY-MM-DD)", n -> LocalDate.parse(n) != null);
        ticketFieldCheckers.put("person height", n -> Double.parseDouble(n) > 0);
        ticketFieldCheckers.put("person weight", n -> Integer.parseInt(n) > 0);
        ticketFieldCheckers.put("person passport ID", (n) -> n.length() >= 10);
    }

    public static String[] readTicketFields() throws CancelCommandException, IncorrectScriptException {
        String[] ticketFields = new String[11];

        int fieldCount = -1;
        for (Map.Entry<String, FieldChecker> entry : ticketFieldCheckers.entrySet()) {
            if (++fieldCount == 0 || fieldCount == 4) continue;

            String ticketField = readField(entry.getKey(), entry.getValue());
            ticketFields[fieldCount] = ticketField;
        }

        ticketFields[0] = null;
        ticketFields[4] = null;

        return ticketFields;
    }

    public static void checkTicketFields(String[] ticketFields) throws IncorrectFieldException {
        if (ticketFields.length != 11) throw new IncorrectFieldException("?");

        int fieldCount = 0;
        for (FieldChecker checker : ticketFieldCheckers.values()) checkField(ticketFields[fieldCount++], checker);
    }

    public static Person createPerson(String[] personFields) {
        LocalDate birthday = LocalDate.parse(personFields[0]);
        double height = Double.parseDouble(personFields[1]);
        int weight = Integer.parseInt(personFields[2]);
        String passportID = personFields[3];

        return new Person(birthday, height, weight, passportID);
    }
    public static String[] readPersonFields() throws CancelCommandException, IncorrectScriptException {
        String[] personFields = new String[4];

        personFields[0] = readField("person birthday (YYYY-MM-DD)", n -> LocalDate.parse(n) != null);
        personFields[1] = readField("person height", n -> Double.parseDouble(n) > 0);
        personFields[2] = readField("person weight", n -> Integer.parseInt(n) > 0);
        personFields[3] = readField("person passport ID", (n) -> n.length() >= 10);

        return personFields;
    }

    private static String readField(String target, FieldChecker checker) throws CancelCommandException, IncorrectScriptException {
        boolean wasRead = false;
        String field = null;

        if (InputManager.isConsoleInput()) System.out.printf("Enter %s: ", target);

        while (!wasRead) {
            field = InputManager.readLine();

            try {
                if (field == null) throw new IncorrectArgumentException("empty field");
                if (field.equals("-1")) throw new CancelCommandException();

                try {
                    checkField(field, checker);
                } catch (IncorrectFieldException e) {
                    if (!InputManager.isConsoleInput()) throw new IncorrectScriptException("invalid element: " + field);
                    throw new IncorrectArgumentException(e.getMessage());
                }

                wasRead = true;
            } catch (IncorrectArgumentException e) {
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
        return field == null || field.trim().equals("");
    }

    public static String toCSV(Ticket ticket) {
        return ticket.toCSV();
    }
}
