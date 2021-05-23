package lab5.ticket;

import lab5.CollectionManager;
import lab5.console.commands.CommandReader;
import lab5.exceptions.CancelCommandException;
import lab5.exceptions.IncorrectArgumentException;
import lab5.exceptions.IncorrectFieldException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class TicketManager {
    private static final LinkedHashMap<String, FieldChecker> ticketFieldCheckers = new LinkedHashMap<>();
    private static final HashSet<String> personFieldsNames = new LinkedHashSet<>();
    private static final HashSet<String> ticketFieldsNames = new LinkedHashSet<>();

    static {
        ticketFieldCheckers.put("id", n -> Long.parseLong(n) > 0 && !CollectionManager.getIdSet().contains(Long.parseLong(n)));
        ticketFieldCheckers.put("name", null);
        ticketFieldCheckers.put("x coordinate", n -> Long.parseLong(n) <= 565 || Long.parseLong(n) > 0);
        ticketFieldCheckers.put("y coordinate", n -> Integer.parseInt(n) <= 907 || Integer.parseInt(n) > 0);
        ticketFieldCheckers.put("creation date", n -> Long.parseLong(n) < System.currentTimeMillis());
        ticketFieldCheckers.put("price", n -> Integer.parseInt(n) > 0);
        ticketFieldCheckers.put("ticket type", n -> Arrays.stream(TicketType.values()).anyMatch(v -> v.toString().equals(n)));
        ticketFieldCheckers.put("person birthday", n -> LocalDate.parse(n) != null);
        ticketFieldCheckers.put("person height", n -> Double.parseDouble(n) > 0);
        ticketFieldCheckers.put("person weight", n -> Integer.parseInt(n) > 0);
        ticketFieldCheckers.put("person passport ID", (n) -> n.length() >= 10);

        personFieldsNames.add("person birthday");
        personFieldsNames.add("person height");
        personFieldsNames.add("person weight");
        personFieldsNames.add("person passport ID");

        ticketFieldsNames.add("name");
        ticketFieldsNames.add("x coordinate");
        ticketFieldsNames.add("y coordinate");
        ticketFieldsNames.add("price");
        ticketFieldsNames.add("ticket type");
        ticketFieldsNames.addAll(personFieldsNames);
    }

    public static Ticket createTicket(HashMap<String, String> ticketFields) {
        String idString = ticketFields.get("id");
        long id = idString == null ? (long) (Math.random() * 1000000000) : Long.parseLong(idString);

        String name = ticketFields.get("name");

        Long x = Long.valueOf(ticketFields.get("x coordinate"));
        Integer y = Integer.valueOf(ticketFields.get("y coordinate"));
        Coordinates coordinates = new Coordinates(x, y);

        String creationDateString = ticketFields.get("creation date");
        Date creationDate = new Date(creationDateString == null ? System.currentTimeMillis() : Long.parseLong(creationDateString));

        int price = Integer.parseInt(ticketFields.get("price"));

        TicketType ticketType = TicketType.valueOf(ticketFields.get("ticket type"));

        LocalDate birthday = LocalDate.parse(ticketFields.get("person birthday"));
        double height = Double.parseDouble(ticketFields.get("person height"));
        int weight = Integer.parseInt(ticketFields.get("person weight"));
        String passportID = ticketFields.get("person passport ID");
        Person person = new Person(birthday, height, weight, passportID);

        return new Ticket(id, name, coordinates, creationDate, price, ticketType, person);
    }

    public static HashMap<String, String> readTicketFields() {
        return readFields(ticketFieldsNames);
    }

    public static HashMap<String, String> parseTicketFields(String ticketFields) throws IncorrectFieldException {
        String[] ticketFieldsArray = ticketFields.split(" *, *");
        if (ticketFieldsArray.length != 11) throw new IncorrectFieldException("?");
        HashMap<String, String> parsedTicketFields = new HashMap<>();

        int i = 0;
        for (Map.Entry<String, FieldChecker> entry : ticketFieldCheckers.entrySet()) {
            String ticketField = ticketFieldsArray[i++];

            checkField(ticketField, entry.getValue());
            parsedTicketFields.put(entry.getKey(), ticketField);
        }

        return parsedTicketFields;
    }

    public static Person createPerson(HashMap<String, String> personFields) throws CancelCommandException {
        LocalDate birthday = LocalDate.parse(personFields.get("person birthday"));
        double height = Double.parseDouble(personFields.get("person height"));
        int weight = Integer.parseInt(personFields.get("person weight"));
        String passportID = personFields.get("person passport ID");

        return new Person(birthday, height, weight, passportID);
    }

    public static HashMap<String, String> readPersonFields() {
        return readFields(personFieldsNames);
    }

    private static HashMap<String, String> readFields(HashSet<String> fieldsNames) throws CancelCommandException {
        HashMap<String, String> fields = new HashMap<>();
        for (String fieldsName : fieldsNames) fields.put(fieldsName, readField(fieldsName, ticketFieldCheckers.get(fieldsName)));

        return fields;
    }

    private static String readField(String target, FieldChecker checker) throws CancelCommandException {

        if (CommandReader.fromConsole) System.out.printf("Enter %s: ", target);
        String field = CommandReader.nextLine();

        while (true) {
            try {
                try {
                    checkField(field, checker);
                } catch (IncorrectFieldException e) {
                    throw new IncorrectArgumentException(e.getMessage());
                }

                return field;
            } catch (IncorrectArgumentException e) {
                field = CommandReader.processIncorrectInput(e.getMessage());
            }
        }
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

    private interface FieldChecker {
        boolean check(String field);
    }

    public static String toCSV(Ticket ticket) {
        return ticket.toCSV();
    }
}
