package lab5.console.commands;

import lab5.CollectionManager;

public class Show extends Command {
    protected Show() {
        super("show", "show description");
    }

    @Override
    protected void execute(String argument) {
        System.out.println(CollectionManager.getSize() == 0
                ? "Collection is empty"
                : "All elements of the collection:\n" + CollectionManager.convertToString().trim());
    }
}
