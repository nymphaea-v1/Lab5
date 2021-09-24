package lab5;

import lab5.ticket.TicketReader;

public class Main {
    public static void main(String[] args) {
        String filePath = args.length == 0 ? "input.csv" : args[0];

        TicketReader ticketReader = new TicketReader(0);
        CollectionManager collectionManager = new CollectionManager(filePath, ticketReader);
        InputReader inputReader = new InputReader();
        CommandManager commandManager = new CommandManager(collectionManager, inputReader, ticketReader);

        inputReader.startReading(commandManager);
    }
}