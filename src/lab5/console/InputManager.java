package lab5.console;

import lab5.console.commands.CommandManager;
import lab5.exceptions.CancelCommandException;
import lab5.exceptions.NoSuchCommandException;

import java.util.LinkedList;
import java.util.Scanner;

public class InputManager {
    private static final LinkedList<Scanner> scanners = new LinkedList<>();

    public static boolean isConsoleInput() {
        return scanners.size() == 1;
    }

    public static void readCommands() {
        scanners.add(new Scanner(System.in));

        while (!scanners.isEmpty()) {
            Scanner scanner = scanners.getLast();

            if (isConsoleInput() || scanner.hasNext()) {
                String nextCommand = scanner.nextLine();

                if (nextCommand.isEmpty()) continue;

                try {
                    CommandManager.execute(nextCommand);
                } catch (NoSuchCommandException e) {
                    System.out.println(e.getMessage());
                    if (!isConsoleInput()) toConsoleInput();
                }
            } else scanners.removeLast();
        }
    }

    public static void stop() {
        scanners.clear();
    }

    public static String readNext() {
        String nextLine = scanners.getLast().nextLine().trim();
        return nextLine.equals("") ? null : nextLine;
    }

    public static void addFileScanner(Scanner scanner) {
        scanners.addLast(scanner);
    }

    public static String processIncorrectInput(String message) throws CancelCommandException {
        if (isConsoleInput()) {
            System.out.println(message);

            while (true) {
                String input = readNext();
                if (input == null) {
                    System.out.println("Input can't be empty. Try again or type -1 to cancel:");
                } else if (input.equals("-1")) {
                    throw new CancelCommandException();
                } else return input;
            }
        } else {
            toConsoleInput();
            throw new CancelCommandException(message);
        }
    }

    private static void toConsoleInput() {
        Scanner scanner = scanners.getFirst();

        scanners.clear();
        scanners.add(scanner);
    }
}
