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
            String input = scanner.nextLine();

            try {
                if (!input.isEmpty()) CommandManager.executeCommand(input);
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
        String output = scanner.nextLine();

        return output.equals("") ? null : output;
    }
}
