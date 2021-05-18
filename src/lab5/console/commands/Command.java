package lab5.console.commands;

import lab5.exceptions.CancelCommandException;
import lab5.exceptions.IncorrectArgumentException;

abstract class Command {
    protected final String code;
    protected final String description;
    protected final String pattern;

    protected Command(String code, String description, String pattern) {
        this.code = code;
        this.description = description;
        this.pattern = pattern;
    }

    protected Command(String code, String description) {
        this.code = this.pattern = code;
        this.description = description;
    }

    protected String getCode() {
        return code;
    }

    protected String getDescription() {
        return description;
    }

    protected String getPattern() {
        return pattern;
    }

    abstract protected void execute(String argument) throws IncorrectArgumentException, CancelCommandException;
}
