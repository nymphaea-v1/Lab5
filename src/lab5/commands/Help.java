package lab5.commands;

import lab5.CommandManager;

/**
 * Basic command.
 * Displays all available commands with descriptions given in their fields.
 *
 * @see lab5.commands.Command
 * @see lab5.CommandManager
 */

public class Help extends Command {
    private final CommandManager commandManager;

    public Help(CommandManager commandManager) {
        super("help", "display list of all available commands");
        this.commandManager = commandManager;
    }

    @Override
    public void execute(String argument) {
        System.out.println("List of all available commands:");
        for (Command command : commandManager.getCommands()) {
            System.out.println(command.getPattern() + " - " + command.getDescription());
        }
    }
}
