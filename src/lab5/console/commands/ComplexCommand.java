package lab5.console.commands;

import lab5.exceptions.CancelCommandException;
import lab5.exceptions.IncorrectInputException;

abstract class ComplexCommand extends Command {
    protected final String pattern;

    protected ComplexCommand(String name, String description, String pattern) {
        super(name, description);
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

    abstract protected void execute(String argument) throws IncorrectInputException, CancelCommandException;
}
