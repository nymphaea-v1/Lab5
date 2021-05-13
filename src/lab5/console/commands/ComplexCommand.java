package lab5.console.commands;


import lab5.exceptions.IncorrectArgumentException;

abstract class ComplexCommand extends Command {
    protected String pattern;

    protected ComplexCommand(String name, String description, String pattern) {
        super(name, description);
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

    abstract void execute(String argument) throws IncorrectArgumentException;
}
