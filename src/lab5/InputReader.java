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
        Scanner consoleScanner = new Scanner(System.in);
        consoleScanner.useDelimiter("\n");

        scanners.add(consoleScanner);

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
                argument = next.substring(++spaceIndex);
            } else command = next.toLowerCase();

            do {
                try {
                    CommandManager.execute(command, argument);
                } catch (IncorrectArgumentException | NoSuchCommandException | CancelCommandException e) {
                    System.out.println(command + ": " + e.getMessage());

                    if (!fromConsole) toConsole();
                    else if (e instanceof NoSuchCommandException) System.out.println("Type help to get a list of all available commands");
                    else if (e instanceof IncorrectArgumentException) {
                        System.out.println("Try again or type -1 to cancel: ");

                        argument = scanner.nextLine();
                        if (!argument.equals("-1")) continue;

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

    public static void addFileToScan(String path) throws FileNotFoundException {
        File file = new File(path);
        String absolutePath = file.getAbsolutePath();

        if (filePaths.contains(absolutePath)) {
            System.out.println("Recursion");

            toConsole();
            return;
        }

        Scanner scanner = new Scanner(file).useDelimiter("\r\n");

        scanners.addLast(scanner);
        filePaths.addLast(absolutePath);

        fromConsole = false;
    }

    public static void removeLastScanner() {
        scanners.removeLast();
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

    public static List<Object> readObject(List<NamedReader> readers) {
        return fromConsole ? readObjectConsole(readers) : readObjectFile(readers);
    }

    public static List<Object> readObject(List<NamedReader> readers, Scanner scanner) throws IncorrectFieldException {
        List<Object> result = new ArrayList<>();
        for (NamedReader reader : readers) {
            if (!scanner.hasNext()) throw new IncorrectFieldException("end of file");
            result.add(reader.reader.read(scanner));
        }
        return result;
    }

    private static List<Object> readObjectFile(List<NamedReader> readers) {
        try {
            return readObject(readers, scanners.getLast());
        } catch (IncorrectFieldException e) {
            throw new CancelCommandException(e.getMessage());
        }
    }

    private static List<Object> readObjectConsole(List<NamedReader> readers) {
        List<Object> result = new ArrayList<>();
        Scanner scanner = scanners.getFirst();

        for (NamedReader reader : readers) {
            Object element;
            while (true) {
                try {
                    System.out.println("Enter " + reader.name + ":");

                    element = reader.reader.read(scanner);
                    if (element.equals("-1")) throw new CancelCommandException();

                    break;
                } catch (IncorrectFieldException e) {
                    String message = e.getMessage();
                    if (message.equals("-1")) throw new CancelCommandException();
                    System.out.println("Invalid input (" + message + "). Try again:");
                }
            }

            result.add(element);
        }

        return result;
    }

    public static class NamedReader {
        public final String name;
        public final Reader reader;

        public NamedReader(String name, Reader reader) {
            this.name = name;
            this.reader = reader;
        }

        public interface Reader {
            Object read(Scanner scanner) throws IncorrectFieldException;
        }
    }
}