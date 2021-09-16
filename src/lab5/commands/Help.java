package lab5.commands;

import lab5.CommandManager;

public class Help extends Command {
    public Help() {
        super("help", "display list of all available commands");
    }

    @Override
    public void execute(String argument) {
        System.out.println("List of all available commands:");
        for (Command command : CommandManager.getCommands()) {
            System.out.println(command.getPattern() + " - " + command.getDescription());
        }
    }
}
