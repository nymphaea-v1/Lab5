package lab5.commands;

import lab5.CollectionManager;

public class Clear extends Command {
    public Clear() {
        super("clear", "clear this collection", "clear");
    }

    @Override
    public void execute(String argument) {
        CollectionManager.clear();
        System.out.println("Collection has been cleared");
    }
}
