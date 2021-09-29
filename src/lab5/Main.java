package lab5;

public class Main {
    public static void main(String[] args) {
        String filePath = args.length == 0 ? "input.csv" : args[0];

        CollectionManager collectionManager = new CollectionManager(filePath);
        InputReader inputReader = new InputReader();
        CommandManager commandManager = new CommandManager(collectionManager, inputReader);

        inputReader.startReading(commandManager);
    }
}
