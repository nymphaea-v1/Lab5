package lab5.console.commands;

abstract class Command {
    protected final String name;
    protected final String description;

    protected Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    protected String getName() {
        return name;
    }

    protected String getDescription() {
        return description;
    }
}
