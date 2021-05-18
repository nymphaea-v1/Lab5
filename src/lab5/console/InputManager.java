package lab5.console;

import lab5.console.commands.CommandManager;
import lab5.exceptions.NoSuchCommandException;

import java.util.Scanner;

public class InputManager {
    private static final Scanner consoleScanner = new Scanner(System.in);
    private static Scanner scanner = consoleScanner;
    private static boolean isRunning = false;
    private static boolean consoleInput = true;

    public static boolean isConsoleInput() {
        return consoleInput;
    }

    public static void run() {
        isRunning = true;

        while (isRunning) {
            String nextCommand = consoleScanner.nextLine();

            try {
                if (!nextCommand.isEmpty()) CommandManager.execute(nextCommand);
            } catch (NoSuchCommandException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void stop() {
        scanner.close();
        isRunning = false;
    }

    public static String readLine() {
        String nextLine = scanner.nextLine().trim();

        return nextLine.equals("") ? null : nextLine;
    }

    public static void toFileScanner(Scanner scanner) {
        InputManager.scanner = scanner;
        consoleInput = false;
    }

    private static void toConsoleScanner() {
        InputManager.scanner = consoleScanner;
        consoleInput = true;
    }
}
