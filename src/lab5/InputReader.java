package lab5;

import lab5.console.commands.CommandManager;
import lab5.exceptions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class InputReader {
    private static final LinkedList<Scanner> scanners = new LinkedList<>();
    private static final LinkedList<String> filePaths = new LinkedList<>();
    public static boolean fromConsole = true;

    public static void startReading() {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");

        scanners.add(sc);

        while (!scanners.isEmpty()) {
            Scanner scanner = scanners.getLast();

            if (fromConsole || scanner.hasNext()) {
                String next = scanner.nextLine().trim();

                if (next.isEmpty()) continue;

                String command;
                String argument = null;

                int spaceIndex = next.indexOf(" ");
                if (spaceIndex != -1) {
                    command = next.substring(0, spaceIndex).toLowerCase();
                    argument = next.substring(spaceIndex+1);
                } else command = next.toLowerCase();

                if (fromConsole) {
                    while (true) {
                        try {
                            CommandManager.execute(command, argument);
                            break;
                        } catch (IncorrectArgumentException e) {
                            System.out.println(e.getMessage() + " Try again or write type -1 to cancel");

                            argument = scanner.nextLine();
                            if (argument.equals("-1")) {
                                System.out.println("Current command execution has been cancelled");
                                break;
                            }
                        } catch (NoSuchCommandException e) {
                            System.out.println(e.getMessage() + " Type help to get a list of all available commands");
                            break;
                        } catch ( CancelCommandException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                    }
                } else {
                    try {
                        CommandManager.execute(command, argument);
                    } catch (NoSuchCommandException | IncorrectArgumentException | CancelCommandException e) {
                        System.out.println(e.getMessage());
                        toConsole();
                    }
                }
            } else removeLastScanner();
        }
    }

    public static void stopReading() {
        scanners.clear();
        filePaths.clear();

        fromConsole = true;
    }

    public static void addFileToScan(File file, String delimiter) throws FileNotFoundException {
        String filePath = file.getAbsolutePath();

        if (filePaths.contains(filePath)) {
            System.out.println("Recursion");

            toConsole();
            return;
        }

        Scanner scanner = new Scanner(file);
        scanner.useDelimiter(delimiter);
        scanners.addLast(scanner);

        filePaths.addLast(filePath);

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

    public static List<Object> readObject(List<PairReader> readers) {
        return fromConsole ? readObjectConsole(readers) : readObjectFile(readers);
    }

    private static List<Object> readObjectFile(List<PairReader> readers) {
        List<Object> result = new ArrayList<>();
        Scanner scanner = scanners.getLast();

        for (PairReader reader : readers) {
            try {
                result.add(reader.reader.read(scanner));
            } catch (IncorrectFieldException e) {
                throw new CancelCommandException(e.getMessage());
            }
        }

        return result;
    }

    private static List<Object> readObjectConsole(List<PairReader> readers) {
        List<Object> result = new ArrayList<>();
        Scanner scanner = scanners.getFirst();

        for (PairReader reader : readers) {
            Object element;
            while (true) {
                try {
                    System.out.println("Enter " + reader.name + ":");
                    element = reader.reader.read(scanner);
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

    public static class PairReader {
        public final String name;
        public final Reader reader;

        public PairReader(String name, Reader reader) {
            this.name = name;
            this.reader = reader;
        }
    }

    public interface Reader {
        Object read(Scanner scanner) throws IncorrectFieldException;
    }
}
