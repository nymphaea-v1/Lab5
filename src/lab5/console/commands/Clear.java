package lab5.console.commands;

import lab5.CollectionManager;

public class Clear extends SimpleCommand {
    protected Clear() {
        super("clear", "clear description");
    }

    @Override
    protected void execute() {
        CollectionManager.clearCollection();

        System.out.println("Collection has been cleared");
    }
}
