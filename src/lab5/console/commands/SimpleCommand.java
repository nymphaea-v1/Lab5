package lab5.console.commands;

import lab5.exceptions.CancelCommandException;

public abstract class SimpleCommand extends Command {
    protected SimpleCommand(String name, String description) {
        super(name, description);
    }

    protected abstract void execute() throws CancelCommandException;
}
