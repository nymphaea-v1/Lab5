package lab5;

import lab5.CSVParser.CSVParserButBetter;
import lab5.CSVParser.CSVParserButBetter.CSVParsingException;
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
    private long nextId;

    public CollectionManager(String filePathString) {
        try {
            filePath = Paths.get(filePathString);
            nextId = 0;

            addFromFile(filePath);
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

    public long getNextId() {
        return nextId;
    }

    public int getSize() {
        return collection.size();
    }

    public Ticket getElementById(long id) {
        for (Map.Entry<Long, Ticket> element : collection.entrySet()) {
            if (element.getValue().getId() == id) return element.getValue();
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

    public boolean setElement(Long key, Ticket ticket) {
        long id = ticket.getId();

        if (getElementById(id) != null) return false;

        collection.put(key, ticket);
        if (id >= nextId) nextId = id + 1;
        return true;
    }

    public boolean removeElement(Long key) {
        return collection.remove(key) != null;
    }

    public void addFromFile(Path filePath) throws IOException {
        CSVParserButBetter parser = new CSVParserButBetter(filePath);
        while (parser.hasNext()) {
            try {
                Long key = Long.parseLong(parser.next());
                Ticket ticket = TicketReader.readTicket(parser);
                if (!setElement(key, ticket)) {
                    System.out.println("Elements with the same id were found, the last one was skipped");
                }
            } catch (IncorrectFieldException | CSVParsingException | NumberFormatException e) {
                if (parser.hasNext() && !parser.lineSkip) parser.skipLine();
                System.out.println("Object initialization failed: " + e.getMessage());
            } catch (NoSuchElementException e) {
                System.out.println("Object initialization failed: the end of file");
            }
        }

        parser.close();
    }

    public void clear() {
        collection.clear();
    }

    public void save() throws IOException {
        FileOutputStream outputStream = new FileOutputStream(filePath.toFile());
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);

        for (Map.Entry<Long, Ticket> element : collection.entrySet()) {
            outputStreamWriter.write(convertToCSV(element) + "\n");
        }

        outputStreamWriter.close();
    }

    public void print() {
        for (Map.Entry<Long, Ticket> element : collection.entrySet()) {
            System.out.println(element.getKey() + ": " + element.getValue());
        }
    }

    private static String convertToCSV(Map.Entry<Long, Ticket> element) {
        return element.getKey() + ", " + element.getValue().toCSV();
    }

    private static void sortByCreationDate(CollectionManager collectionManager) {
        LinkedHashMap<Long, Ticket> collectionClone = new LinkedHashMap<>(collectionManager.collection);

        collectionManager.clear();

        collectionClone.entrySet().stream()
            .sorted(Comparator.comparing(n -> n.getValue().getCreationDate()))
            .forEach(element -> collectionManager.setElement(element.getKey(), element.getValue()));
    }
}