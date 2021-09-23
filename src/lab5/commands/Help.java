package lab5.commands;

import lab5.CollectionManager;

import java.util.Collection;

public class Help extends Command {
    private final Collection<Command> commands;

    public Help(Collection<Command> commands) {
        super("help", "display list of all available commands");
        this.commands = commands;
    }

    @Override
    public void execute(String argument, CollectionManager collectionManager) {
        System.out.println("List of all available commands:");
        for (Command command : commands) {
            System.out.println(command.getPattern() + " - " + command.getDescription());
        }
    }
}
