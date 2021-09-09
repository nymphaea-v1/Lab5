package lab5;

import lab5.console.commands.CommandManager;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        String filePath = args.length == 0 ? "input.csv" : args[0];

        CollectionManager.initialize(new File(filePath));
        CommandManager.setCommands();
        InputReader.startReading();
    }
}