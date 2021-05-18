package lab5.console.commands;

import java.util.Collection;

class Help extends Command {
    protected Help() {
        super("help", "help command description");
    }

    @Override
    protected void execute(String argument) {
        Collection<Command> commandCollection = getCommandCollection();
        System.out.println("List of all available commands:");
        for (Command command : commandCollection) {
            System.out.println(command.getPattern() + " - " + command.getDescription());
        }
    }

    private Collection<Command> getCommandCollection() {
        return CommandManager.getCommands();
    }
}
