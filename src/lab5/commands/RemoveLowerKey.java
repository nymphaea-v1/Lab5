package lab5.commands;

import lab5.CollectionManager;
import lab5.exceptions.IncorrectArgumentException;

/**
 * Basic command with an argument.
 * Allows removing from the collection all elements whose key is lower than the given one.
 *
 * @see lab5.commands.Command
 * @see lab5.CommandManager
 * @see CollectionManager
 */

public class RemoveLowerKey extends Command {
    private final CollectionManager collectionManager;

    public RemoveLowerKey(CollectionManager collectionManager) {
        super("remove_lower_key", "remove all elements with a key lower than specified", "remove_lower_key key");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String keyString) throws IncorrectArgumentException {
        if (keyString == null) throw new IncorrectArgumentException("no argument");

        try {
            Long key = Long.parseLong(keyString);
            int sizeBefore = collectionManager.getSize();

            collectionManager.getEntrySet().removeIf(n -> n.getKey() < key);
            System.out.println("Elements removed: " + (sizeBefore - collectionManager.getSize()));
        } catch (NumberFormatException e) {
            throw new IncorrectArgumentException(keyString + " is not a valid number");
        }
    }
}
