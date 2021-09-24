package lab5.commands;

import lab5.CollectionManager;
import lab5.exceptions.CancelCommandException;
import lab5.exceptions.IncorrectArgumentException;

public abstract class Command {
    private final String name;
    private final String description;
    private final String pattern;

    public Command(String name, String description, String pattern) {
        this.name = name;
        this.description = description;
        this.pattern = pattern;
    }

    public Command(String name, String description) {
        this.name = this.pattern = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPattern() {
        return pattern;
    }

    abstract public void execute(String argument) throws IncorrectArgumentException, CancelCommandException;
}
