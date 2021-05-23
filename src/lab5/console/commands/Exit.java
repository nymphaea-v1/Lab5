package lab5.console.commands;

class Exit extends Command {
    protected Exit() {
        super("exit", "exit description");
    }

    @Override
    protected void execute(String argument) {
        CommandReader.stopReading();
    }
}
