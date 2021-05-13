package lab5.ticket;

import lab5.exceptions.IncorrectFieldException;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.util.Date;

public class TicketFieldsParser {
    
    public static long parseId(String idStr) throws IncorrectFieldException {
        checkEmptyField(idStr);
        
        try {
            long id = Long.parseLong(idStr);
            if (id <= 0) throw new IncorrectFieldException("negative id");

            return id;
        } catch (NumberFormatException e) {
            throw new IncorrectFieldException("invalid id");
        }
    }

    public static String parseName(String name) throws IncorrectFieldException {
        checkEmptyField(name);
        return name;
    }

    public static Coordinates parseCoordinates(String[] coordinatesFields) throws IncorrectFieldException {
        for (String field : coordinatesFields) checkEmptyField(field);
        Coordinates coordinates;
        
        try {
            coordinates = new Coordinates(Long.parseLong(coordinatesFields[0]), Integer.parseInt(coordinatesFields[1]));
        } catch (NumberFormatException e) {
            throw new IncorrectFieldException("invalid coordinates");
        }

        return coordinates;
    }

    public static Date parseCreationDate(String creationDateStr) throws IncorrectFieldException {
        checkEmptyField(creationDateStr);
        Date creationDate;
        
        try {
            creationDate = new Date(Long.parseLong(creationDateStr));
        } catch (NumberFormatException e) {
            throw new IncorrectFieldException("invalid date");
        }

        return creationDate;
    }

    public static int parsePrice(String priceStr) throws IncorrectFieldException {
        checkEmptyField(priceStr);
        int price;
        
        try {
            price = Integer.parseInt(priceStr);
            if (price <= 0) throw new IncorrectFieldException("negative price");

        } catch (NumberFormatException | IncorrectFieldException e) {
            throw new IncorrectFieldException("invalid price");
        }

        return price;
    }

    public static TicketType parseType(String typeStr) throws IncorrectFieldException {
        checkEmptyField(typeStr);
        TicketType ticketType;
        
        try {
            ticketType = TicketType.valueOf(typeStr);
        } catch (IllegalArgumentException e) {
            throw new IncorrectFieldException("invalid ticket type");
        }
        
        return ticketType;
    }

    public static Person parsePerson(String[] personFieldsStr) throws IncorrectFieldException {
        for (String field : personFieldsStr) checkEmptyField(field);
        Person person;
        
        try {
            LocalDate birthday = LocalDate.parse(personFieldsStr[0]);
            double height = Double.parseDouble(personFieldsStr[1]);
            int weight = Integer.parseInt(personFieldsStr[2]);
            String passportID = personFieldsStr[3];

            person = new Person (birthday, height, weight, passportID);
        } catch (NumberFormatException | DateTimeParseException e) {
            throw new IncorrectFieldException("invalid person date");
        }
        
        return person;
    }

    private static void checkEmptyField(String string) throws IncorrectFieldException {
        if (string == null || string.equals("")) throw new IncorrectFieldException("empty field");
    }
}
