package lab5;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        String filePath = args.length == 0 ? "input.csv" : args[0];

        CollectionManager.initialize(new File(filePath));
        CommandManager.setCommands();
        InputReader.startReading();
    }
}