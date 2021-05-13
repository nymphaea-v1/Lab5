package lab5.console.commands;

import lab5.CollectionManager;

public class Show extends SimpleCommand {
    protected Show() {
        super("show", "show description");
    }

    @Override
    protected void execute() {
        System.out.println("All elements of the collection:\n" + CollectionManager.convertToString());
    }
}
