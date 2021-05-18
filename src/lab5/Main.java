package lab5;

import lab5.console.InputManager;
import lab5.console.commands.CommandManager;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            String filePath = args.length == 0 ? "input.csv" : args[0];

            CollectionManager.initialize(filePath);
            CommandManager.setCommands();
            InputManager.run();
        } catch (IOException e) {
            System.out.printf("Cannot find specified file (%s)", e.getMessage());
        }
    }
}