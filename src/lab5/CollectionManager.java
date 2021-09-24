package lab5;

import lab5.exceptions.IncorrectFieldException;
import lab5.ticket.Ticket;
import lab5.ticket.TicketReader;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CollectionManager {
    private final LinkedHashMap<Long, Ticket> collection = new LinkedHashMap<>();
    private Path filePath;

    public CollectionManager(String filePathString, TicketReader ticketReader) {
        try {
            filePath = Paths.get(filePathString);
            addFromFile(filePath, ticketReader);
        } catch (IOException e) {
            System.out.println("Specified file's access error: " + e.getMessage());
            return;
        }

        if (collection.size() != 0) {
            sortByCreationDate(this);
            System.out.printf("Collection with %d elements has been initialized%n", getSize());
        } else System.out.println("File is empty");
    }

    public Path getFilePath() {
        return filePath;
    }

    public int getSize() {
        return collection.size();
    }

    public Ticket getElementById(long id) {
        for (Map.Entry<Long, Ticket> entry : collection.entrySet()) {
            if (entry.getValue().getId() == id) return entry.getValue();
        }

        return null;
    }

    public Set<Map.Entry<Long, Ticket>> getEntrySet() {
        return collection.entrySet();
    }

    public Collection<Ticket> getValues() {
        return collection.values();
    }

    public boolean containsKey(Long key) {
        return collection.containsKey(key);
    }

    public void setElement(Long key, Ticket ticket) {
        collection.put(key, ticket);
    }

    public boolean removeElement(Long key) {
        return collection.remove(key) != null;
    }

    public void addFromFile(Path filePath, TicketReader ticketReader) throws IOException {
        Scanner scanner = new Scanner(filePath);
        long key = 0;

        while (scanner.hasNext()) {
            try {
                Ticket ticket = ticketReader.readTicket(new Scanner(scanner.nextLine()).useDelimiter(","));
                if (getElementById(ticket.getId()) == null) collection.put(key++, ticket);
                else System.out.println("Elements with the same id were found, the last one was skipped");
            } catch (IncorrectFieldException e) {
                System.out.println("Object initialization failed: " + e.getMessage());
            }
        }

        scanner.close();
    }

    public void clear() {
        collection.clear();
    }

    public void save() throws IOException {
        FileOutputStream outputStream = new FileOutputStream(filePath.toFile());
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);

        outputStreamWriter.write(convertToCSV());
        outputStreamWriter.close();
    }

    public void print() {
        for (Map.Entry<Long, Ticket> entry : collection.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private String convertToCSV() {
        StringBuilder stringBuilder = new StringBuilder(getSize() * 100);

        for (Ticket ticket : collection.values()) {
            stringBuilder.append(ticket.toCSV());
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    private static void sortByCreationDate(CollectionManager collectionManager) {
        LinkedHashMap<Long, Ticket> collectionClone = new LinkedHashMap<>(collectionManager.collection);

        collectionManager.clear();

        collectionClone.entrySet().stream()
            .sorted(Comparator.comparing(n -> n.getValue().getCreationDate()))
            .forEach(entry -> collectionManager.setElement(entry.getKey(), entry.getValue()));
    }
}