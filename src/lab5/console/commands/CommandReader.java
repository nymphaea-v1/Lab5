package lab5.console.commands;

import lab5.exceptions.CancelCommandException;
import lab5.exceptions.IncorrectScriptException;
import lab5.exceptions.NoSuchCommandException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CommandReader {
    private static final LinkedList<Scanner> scanners = new LinkedList<>();
    private static final LinkedList<String> filePaths = new LinkedList<>();
    public static boolean fromConsole = true;

    public static void startReading() {
        scanners.add(new Scanner(System.in));

        while (!scanners.isEmpty()) {
            Scanner scanner = scanners.getLast();

            if (fromConsole || scanner.hasNext()) {
                String nextCommand = scanner.nextLine();

                if (nextCommand.isEmpty()) continue;

                try {
                    CommandManager.execute(nextCommand);
                } catch (NoSuchCommandException e) {
                    System.out.println(e.getMessage());
                    if (!fromConsole) toConsole();
                }
            } else {
                scanners.removeLast();
                filePaths.removeLast();

                if (filePaths.isEmpty()) fromConsole = true;
            }
        }
    }

    public static void stopReading() {
        scanners.clear();
        filePaths.clear();

        fromConsole = true;
    }

    public static String nextLine() {
        String nextLine = scanners.getLast().nextLine().trim();
        return nextLine.equals("") ? null : nextLine;
    }

    public static void addFile(File file) throws FileNotFoundException, IncorrectScriptException {
        String filePath = file.getAbsolutePath();

        if (filePaths.contains(filePath)) {
            toConsole();
            throw new IncorrectScriptException("recursion");
        }

        scanners.addLast(new Scanner(file));
        filePaths.addLast(filePath);

        fromConsole = false;
    }

    public static String processIncorrectInput(String message) throws CancelCommandException {
        if (fromConsole) {
            System.out.println(message);

            while (true) {
                String input = nextLine();
                if (input == null) {
                    System.out.println("Input can't be empty. Try again or type -1 to cancel:");
                } else if (input.equals("-1")) {
                    throw new CancelCommandException();
                } else return input;
            }
        } else {
            toConsole();
            throw new CancelCommandException(message);
        }
    }

    private static void toConsole() {
        Scanner scanner = scanners.getFirst();

        scanners.clear();
        filePaths.clear();

        scanners.add(scanner);

        fromConsole = true;
    }
}
