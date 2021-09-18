package lab5;

public class Main {
    public static void main(String[] args) {
        String filePath = args.length == 0 ? "input.csv" : args[0];

        CollectionManager.initialize(filePath);
        CommandManager.setCommands();
        InputReader.startReading();
    }
}