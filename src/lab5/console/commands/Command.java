package lab5.console.commands;

abstract class Command {
    protected String name;
    protected String description;

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
