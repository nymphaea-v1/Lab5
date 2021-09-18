package lab5;

import lab5.ticket.Ticket;
import lab5.ticket.TicketReader;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CollectionManager {
    private static final LinkedHashMap<Long, Ticket> collection = new LinkedHashMap<>();
    private static Path filePath;

    public static Path getFilePath() {
        return filePath;
    }

    public static int getSize() {
        return collection.size();
    }

    public static Ticket getElementById(long id) {
        for (Map.Entry<Long, Ticket> entry : collection.entrySet()) {
            if (entry.getValue().getId() == id) return entry.getValue();
        }

        return null;
    }

    public static Set<Map.Entry<Long, Ticket>> getEntrySet() {
        return collection.entrySet();
    }

    public static Collection<Ticket> getValues() {
        return collection.values();
    }

    public static boolean containsKey(Long key) {
        return collection.containsKey(key);
    }

    public static void setElement(Long key, Ticket ticket) {
        collection.put(key, ticket);
    }

    public static boolean removeElement(Long key) {
        return collection.remove(key) != null;
    }

    public static void addFromFile(Path filePath) throws IOException {
        InputReader.addToScan(filePath.toString());
        long key = 0;

        while (true) {
            try {
                collection.put(key++, TicketReader.read(false));
            } catch (InputReader.CannotReadObjectException e) {
                if (e.getMessage().equals("end of file")) break;

                System.out.println("Object initialization failed: " + e.getMessage());
            }
        }
    }

    public static void initialize(String filePathString) {
        try {
            filePath = Paths.get(filePathString);
            System.out.println(filePath);
            addFromFile(filePath);
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
        for (Map.Entry<Long, Ticket> entry : collection.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static String convertToCSV() {
        StringBuilder stringBuilder = new StringBuilder(getSize() * 100);

        for (Ticket ticket : collection.values()) {
            stringBuilder.append(ticket.toCSV());
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    private static void sortByCreationDate() {
        LinkedHashMap<Long, Ticket> collectionClone = new LinkedHashMap<>(collection);

        collection.clear();

        collectionClone.entrySet().stream()
            .sorted(Comparator.comparing(n -> n.getValue().getCreationDate()))
            .forEach(entry -> collection.put(entry.getKey(), entry.getValue()));
    }
}