package lab5;

import lab5.exceptions.IncorrectFieldException;
import lab5.exceptions.RepeatingException;
import lab5.ticket.Ticket;
import lab5.ticket.TicketManager;

import java.io.IOException;
import java.util.*;

public class CollectionManager {
    private static final LinkedHashMap<Integer, Ticket> collection = new LinkedHashMap<>();
    private static final HashSet<Long> idSet = new HashSet<>();
    private static String filePath;
    private static long createTimeStamp;
    private static long updateTimeStamp;

    public static int getSize() {
        return collection.size();
    }

    public static String getFilePath() {
        return filePath;
    }

    public static long getCreateTimeStamp() {
        return createTimeStamp;
    }

    public static long getUpdateTimeStamp() {
        return updateTimeStamp;
    }

    public static Set<Map.Entry<Integer, Ticket>> getEntrySet() {
        return collection.entrySet();
    }

    public static Set<Integer> getKeySet() {
        return collection.keySet();
    }

    public static Collection<Ticket> getValueCollection() {
        return collection.values();
    }

    public static void setElement(Integer key, Ticket ticket) {
        collection.put(key, ticket);
        idSet.add(ticket.getId());
    }

    public static boolean containsId(Long id) {
        return idSet.contains(id);
    }

    public static boolean containsKey(Integer key) {
        return collection.containsKey(key);
    }

    public static void initialize(String filePath) throws IOException {
        long[] fileAttributes = FileManager.getFileTimeStamps(filePath);
        createTimeStamp = fileAttributes[0];
        updateTimeStamp = fileAttributes[1];
        CollectionManager.filePath = filePath;

        ArrayList<String> ticketsFieldsString = FileManager.readFile(filePath);

        int key = 0;
        for (String ticketFieldsString : ticketsFieldsString) {
            try {
                String[] ticketFields = ticketFieldsString.split(",");
                TicketManager.checkTicketFields(ticketFields);
                Ticket ticket = TicketManager.createTicket(ticketFields);

                Long id = ticket.getId();
                if (idSet.contains(id)) throw new RepeatingException("id " + id);
                else idSet.add(id);

                collection.put(key++, ticket);
            } catch (IncorrectFieldException | RepeatingException e) {
                System.out.printf("%s. Line %d was skipped%n", e.getMessage(), ++key);
            }
        }

        sort();

        System.out.printf("Collection with %d elements was initialized%n", getSize());
    }

    public static Integer getKeyById(long id) {
        if (!containsId(id)) return null;

        Integer key = null;

        for (Map.Entry<Integer, Ticket> entry : collection.entrySet()) {
            if (entry.getValue().getId() == id) key = entry.getKey();
        }

        return key;
    }

    public static boolean removeElement(Integer key) {
        return collection.remove(key) != null;
    }

    public static void removeElements(Set<Integer> keyList) {
        for (Integer key : keyList) removeElement(key);
    }

    public static void clear() {
        idSet.clear();
        collection.clear();
    }

    public static String convertToString() {
        StringBuilder stringBuilder = new StringBuilder(getSize() * 200);
        for (Map.Entry<Integer, Ticket> entry : collection.entrySet()) {
            stringBuilder.append(entry.getKey());
            stringBuilder.append(": ");
            stringBuilder.append(entry.getValue().toString());
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    public static void save() throws IOException {
        FileManager.writeNewFile(filePath, convertToCSV());

        updateTimeStamp = new Date().getTime();
    }

    private static void sort() {
        LinkedHashMap<Integer, Ticket> collectionClone = new LinkedHashMap<>(collection);

        clear();

        collectionClone.entrySet().stream()
                .sorted(Comparator.comparing(n -> n.getValue().getCreationDate()))
                .forEach(entry -> collection.put(entry.getKey(), entry.getValue()));
    }

    private static String convertToCSV() {
        StringBuilder stringBuilder = new StringBuilder(getSize() * 100);

        for (Ticket ticket : collection.values()) {
            stringBuilder.append(TicketManager.toCSV(ticket));
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}