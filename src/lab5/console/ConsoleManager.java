package lab5.console;

import lab5.console.commands.CommandManager;
import lab5.exceptions.NoSuchCommandException;

import java.util.Scanner;

public class ConsoleManager {
    private static Scanner scanner;
    private static boolean isRunning = false;

    public static void run() {
        scanner = new Scanner(System.in);
        isRunning = true;

        while (isRunning) {
            String nexCommand = scanner.nextLine();

            try {
                if (!nexCommand.isEmpty()) CommandManager.executeCommand(nexCommand);
            } catch (NoSuchCommandException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void stop() {
        scanner.close();
        isRunning = false;
    }

    public static String read() {
        String nextLine = scanner.nextLine().trim();

        return nextLine.equals("") ? null : nextLine;
    }
}
