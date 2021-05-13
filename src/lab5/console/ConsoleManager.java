package lab5.console;

import lab5.console.commands.CommandManager;
import lab5.exceptions.NoSuchCommandException;

import java.util.Scanner;

public class ConsoleManager {
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean isRunning = false;

    public static void run() {
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
        isRunning = false;
    }
}
