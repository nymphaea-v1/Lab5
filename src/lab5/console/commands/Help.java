package lab5.console.commands;

import java.util.Collection;

class Help extends SimpleCommand {
    protected Help() {
        super("help", "help command description");
    }

    @Override
    protected void execute() {
        Collection<Command> commandCollection = getCommandCollection();
        System.out.println("List of all available commands:");
        for (Command command : commandCollection) {
            String output = (command instanceof ComplexCommand ? ((ComplexCommand) command).getPattern() : command.getName()) + " - " + command.getDescription();
            System.out.println(output);
        }
    }

    private Collection<Command> getCommandCollection() {
        return CommandManager.getCommandCollection();
    }
}
