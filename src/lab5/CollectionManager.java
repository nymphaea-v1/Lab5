package lab5;

import lab5.exceptions.IncorrectFieldException;
import lab5.ticket.Ticket;
import lab5.ticket.TicketManager;

import java.io.IOException;
import java.util.*;

public class CollectionManager {
    private static final LinkedHashMap<Long, Ticket> collection = new LinkedHashMap<>();
    private static String filePath;
    private static long creationDate;
    private static long lastModifiedDate;

    public static int getSize() {
        return collection.size();
    }

    public static String getFilePath() {
        return filePath;
    }

    public static long getCreationDate() {
        return creationDate;
    }

    public static long getLastModifiedDate() {
        return lastModifiedDate;
    }

    public static void initialize(String filePath) throws IOException {
        CollectionManager.filePath = filePath;
        long[] fileAttributes = FileManager.getFileDates(filePath);
        creationDate = fileAttributes[0];
        lastModifiedDate = fileAttributes[1];

        ArrayList<String> fields = FileManager.readFile(filePath);

        for (int i = 0; i < fields.size(); i++) {
            try {
                Ticket ticket = TicketManager.createTicket(fields.get(i).split(","));
                collection.put(ticket.getId(), ticket);
            } catch (IncorrectFieldException e) {
                System.out.printf("%s. Line %d was skipped%n", e.getMessage(), i + 1);
            }
        }

        sort();

        System.out.printf("Collection with %d elements was initialized%n", getSize());
    }

    public static ArrayList<Long> getGreaterOrLower(long id, boolean greater) {
        ArrayList<Long> keyList = new ArrayList<>();
        Set<Map.Entry<Long, Ticket>> set = collection.entrySet();

        for (Map.Entry<Long, Ticket> next : set) {
            if (next.getValue().getId() == id) {
                greater = !greater;
            } else if (greater) {
                keyList.add(next.getKey());
            }
        }

        return keyList;
    }

    public static boolean removeElement(Long key) {
        return collection.remove(key) != null;
    }

    public static void removeElements(ArrayList<Long> keyList) {
        for (long key : keyList) removeElement(key);
    }

    public static void clear() {
        collection.clear();
    }

    public static String convertToString() {
        StringBuilder stringBuilder = new StringBuilder(getSize() * 200);
        for (long key : collection.keySet()) {
            stringBuilder.append(key);
            stringBuilder.append(": ");
            stringBuilder.append(collection.get(key).toString());
            stringBuilder.append("\n");
        }

        return stringBuilder.toString().trim();
    }

    public static void save() throws IOException {
        FileManager.writeNewFile(filePath, convertToCSV());

        lastModifiedDate = new Date().getTime();
    }

    private static void sort() {
        List<Ticket> ticketCollection = new ArrayList<>(collection.values());
        Collections.sort(ticketCollection);

        clear();
        for (Ticket ticket : ticketCollection) collection.put(ticket.getId(), ticket);
    }

    private static String convertToCSV() {
        StringBuilder stringBuilder = new StringBuilder(getSize() * 100);

        for (Ticket ticket : collection.values()) {
            stringBuilder.append(TicketManager.toCSV(ticket));
            stringBuilder.append("\n");
        }

        return stringBuilder.toString().trim();
    }
}