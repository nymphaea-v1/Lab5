package lab5.console.commands;

public abstract class SimpleCommand extends Command {
    protected SimpleCommand(String name, String description) {
        super(name, description);
    }

    protected abstract void execute();
}
