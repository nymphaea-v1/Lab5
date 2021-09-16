package lab5;

import lab5.exceptions.IncorrectFieldException;
import lab5.ticket.Ticket;
import lab5.ticket.TicketReader;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class CollectionManager {
    private static final LinkedHashMap<Integer, Ticket> collection = new LinkedHashMap<>();
    private static Path filePath;

    public static Path getFilePath() {
        return filePath;
    }

    public static int getSize() {
        return collection.size();
    }

    public static Ticket getElementById(long id) {
        for (Map.Entry<Integer, Ticket> entry : collection.entrySet()) {
            if (entry.getValue().getId() == id) return entry.getValue();
        }

        return null;
    }

    public static Set<Map.Entry<Integer, Ticket>> getEntrySet() {
        return collection.entrySet();
    }

    public static Collection<Ticket> getValues() {
        return collection.values();
    }

    public static boolean containsKey(Integer key) {
        return collection.containsKey(key);
    }

    public static void setElement(Integer key, Ticket ticket) {
        collection.put(key, ticket);
    }

    public static boolean removeElement(Integer key) {
        return collection.remove(key) != null;
    }

    public static void addFromFile(File file) throws FileNotFoundException {
        Scanner lineScanner = new Scanner(file);
        int key = 0;

        while (lineScanner.hasNext()) {
            Scanner fieldScanner = new Scanner(lineScanner.nextLine()).useDelimiter(", ");
            try {
                collection.put(key++, TicketReader.readTicket(fieldScanner));
            } catch (IncorrectFieldException e) {
                System.out.println("Object initialization failed: " + e.getMessage());
            }
        }
    }

    public static void initialize(File file) {
        try {
            filePath = file.toPath();
            addFromFile(file);
        } catch (IOException e) {
            System.out.println("Specified file's access error");
            return;
        }

        if (collection.size() != 0) {
            sortByCreationDate();
            System.out.printf("Collection with %d elements has been initialized%n", getSize());
        } else System.out.println("File is empty");
    }

    public static void clear() {
        collection.clear();
    }

    public static void save() throws IOException {
        FileOutputStream outputStream = new FileOutputStream(filePath.toFile());
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);

        outputStreamWriter.write(convertToCSV());
        outputStreamWriter.close();
    }

    public static void print() {
        for (Map.Entry<Integer, Ticket> entry : collection.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static String convertToCSV() {
        StringBuilder stringBuilder = new StringBuilder(getSize() * 100);

        for (Ticket ticket : collection.values()) {
            stringBuilder.append(TicketReader.toCSV(ticket));
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    private static void sortByCreationDate() {
        LinkedHashMap<Integer, Ticket> collectionClone = new LinkedHashMap<>(collection);

        collection.clear();

        collectionClone.entrySet().stream()
            .sorted(Comparator.comparing(n -> n.getValue().getCreationDate()))
            .forEach(entry -> collection.put(entry.getKey(), entry.getValue()));
    }
}