package lab5;

import lab5.exceptions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class InputReader {
    private static final LinkedList<Scanner> scanners = new LinkedList<>();
    private static final LinkedList<String> filePaths = new LinkedList<>();
    public static boolean fromConsole = true;

    public static void startReading() {
        Scanner consoleScanner = new Scanner(System.in).useDelimiter("\n");
        scanners.add(consoleScanner);
        fromConsole = true;

        while (!scanners.isEmpty()) {
            Scanner scanner = scanners.getLast();

            if (!(fromConsole || scanner.hasNext())) {
                removeLastScanner();
                continue;
            }

            String next = scanner.nextLine().trim();
            if (next.isEmpty()) continue;

            String command;
            String argument = null;

            int spaceIndex = next.indexOf(" ");
            if (spaceIndex != -1) {
                command = next.substring(0, spaceIndex).toLowerCase();
                argument = next.substring(spaceIndex + 1);
            } else command = next.toLowerCase();

            do {
                try {
                    CommandManager.execute(command, argument);
                } catch (IncorrectArgumentException | NoSuchCommandException | CancelCommandException e) {
                    System.out.println(command + ": " + e.getMessage());

                    if (!fromConsole) toConsole();
                    else if (e instanceof NoSuchCommandException) System.out.println("Type help to get a list of all available commands");
                    else if (e instanceof IncorrectArgumentException) {
                        System.out.println("Try again or type 2? to cancel: ");

                        argument = scanner.nextLine();
                        if (!argument.equals("2?")) continue;

                        System.out.println("Current command execution has been cancelled");
                    }
                }

                break;
            } while (true);
        }
    }

    public static void stopReading() {
        scanners.clear();
        filePaths.clear();

        fromConsole = true;
    }

    public static void addToScan(String path) throws FileNotFoundException {
        File file = new File(path);
        String absolutePath = file.getAbsolutePath();

        if (filePaths.contains(absolutePath)) {
            System.out.println("Recursion");

            toConsole();
            return;
        }

        Scanner scanner = new Scanner(file).useDelimiter("\\r?\\n|\\r");

        scanners.addLast(scanner);
        filePaths.addLast(absolutePath);

        fromConsole = false;
    }

    public static void removeLastScanner() {
        scanners.removeLast().close();
        filePaths.removeLast();

        if (filePaths.isEmpty()) fromConsole = true;
    }

    private static void toConsole() {
        Scanner scanner = scanners.getFirst();

        scanners.clear();
        filePaths.clear();

        scanners.add(scanner);

        fromConsole = true;
    }

    public static List<Object> readObject(List<Reader> readers) {
        return fromConsole ? readObjectConsole(readers) : readObjectFile(readers);
    }

    private static List<Object> readObjectFile(List<Reader> readers) {
        List<Object> result = new ArrayList<>();
        Scanner scanner = scanners.getLast();

        for (Reader reader : readers) {
            if (!scanner.hasNext()) throw new CancelCommandException("end of file");

            try {
                result.add(reader.reader.read(scanner));
            } catch (IncorrectFieldException e) {
                throw new CancelCommandException(e.getMessage());
            }
        }

        return result;
    }

    private static List<Object> readObjectConsole(List<Reader> readers) {
        List<Object> result = new ArrayList<>();
        Scanner scanner = scanners.getFirst();

        for (Reader reader : readers) {
            Object field;

            while (true) {
                try {
                    System.out.println("Enter " + reader.name + ":");

                    field = reader.reader.read(scanner);
                    if (field.toString().trim().equals("2?")) throw new CancelCommandException();

                    break;
                } catch (IncorrectFieldException e) {
                    String message = e.getMessage().trim();
                    if (message.equals("2?")) throw new CancelCommandException();
                    System.out.println("Invalid input (" + message + "). Try again:");
                }
            }

            result.add(field);
        }

        return result;
    }
}