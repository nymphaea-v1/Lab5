package lab5.console.commands;

class Help extends Command {
    protected Help() {
        super("help", "help command description");
    }

    @Override
    protected void execute(String argument) {
        System.out.println("List of all available commands:");
        CommandManager.getCommands().forEach(n -> System.out.println(n.getPattern() + " - " + n.getDescription()));
    }
}
