package lab5.commands;

import lab5.CollectionManager;

/**
 * Basic command.
 * Clears a collection managed by a specified collection manager.
 *
 * @see lab5.commands.Command
 * @see lab5.CommandManager
 * @see CollectionManager
 */

public class Clear extends Command {
    private final CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        super("clear", "clear this collection", "clear");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument) {
        collectionManager.clear();
        System.out.println("Collection has been cleared");
    }
}
