package lab5.console.commands;

import lab5.CollectionManager;

public class Clear extends Command {
    protected Clear() {
        super("clear", "clear description", "clear");
    }

    @Override
    protected void execute(String argument) {
        CollectionManager.clear();

        System.out.println("Collection has been cleared");
    }
}
