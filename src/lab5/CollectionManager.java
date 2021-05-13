package lab5;

import lab5.exceptions.IncorrectFieldException;
import lab5.exceptions.IncorrectFileException;
import lab5.ticket.Ticket;
import lab5.ticket.TicketManager;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class CollectionManager {
    private static LinkedHashMap<Long, Ticket> collection;
    private static Date initDate;

    public static Boolean isInitiated() {
        return (collection != null);
    }

    public static Date getInitDate() {
        return initDate;
    }

    public static Set<Long> getKeySet() {
        return collection.keySet();
    }

    public static int getSize() {
        return collection.size();
    }

    public static long getId(Long key) {
        return collection.get(key).getId();
    }

    public static void initialize(ArrayList<String> fields) throws IncorrectFileException {
        collection = new LinkedHashMap<>();
        for (int i = 0; i < fields.size(); i++) {
            try {
                Ticket ticket = TicketManager.createTicket(fields.get(i).split(","));
                collection.put(ticket.getId(), ticket);
            } catch (IncorrectFieldException e) {
                System.out.println(e.getMessage() + ". Line " + (i+1) + " was skipped");
            }
        }

        if (collection.isEmpty()) throw new IncorrectFileException("No lines was found");

        initDate = new Date();

        System.out.println("Collection was initialized " + initDate);
    }

    public static Ticket removeElement(Long key) {
        return (collection.remove(key));
    }

    public static void removeElements(ArrayList<Long> keyList) {
        for (long key : keyList) {
            removeElement(key);
        }
    }

    public static void clearCollection() {
        collection = new LinkedHashMap<>();
    }

    public static String convertToString() {
        StringBuilder stringBuilder = new StringBuilder(collection.size() * 200);
        for (long key : collection.keySet()) {
            stringBuilder.append(key);
            stringBuilder.append(": ");
            stringBuilder.append(collection.get(key).toString());
            stringBuilder.append("\n");
        }

        return stringBuilder.toString().trim();
    }

    public static void writeCSV(String filepath) throws IOException {
        FileManager.writeNewFile(filepath, convertToCSV());

        System.out.println("Collection has been saved in the file: " + filepath);
    }

    private static ArrayList<String> convertToCSV() {
        ArrayList<String> result = new ArrayList<>();
        for (Ticket ticket : collection.values()) result.add(TicketManager.toCSV(ticket));

        return result;
    }

    public static ArrayList<Long> getGreaterOrLower(long id, boolean greater) {
        ArrayList<Long> keyList = new ArrayList<>();
        Ticket ticket = collection.get(id);

        int value = greater ? 1 : -1;

        for (long key : collection.keySet()) {
            Ticket ticketToCompare = collection.get(key);
            if (ticket.compareTo(ticketToCompare) == value) keyList.add(ticketToCompare.getId());
        }

        return keyList.isEmpty() ? null : keyList;
    }
}
