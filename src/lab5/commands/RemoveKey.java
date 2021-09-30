package lab5.commands;

import lab5.CollectionManager;
import lab5.exceptions.IncorrectArgumentException;

/**
 * Basic command with an argument.
 * Allows removing one element from the collection by a given key.
 *
 * @see lab5.commands.Command
 * @see lab5.CommandManager
 * @see CollectionManager
 */

public class RemoveKey extends Command {
    private final CollectionManager collectionManager;

    public RemoveKey(CollectionManager collectionManager) {
        super("remove_key", "remove element with specified key", "remove_key key");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String keyString) throws IncorrectArgumentException {
        if (keyString == null) throw new IncorrectArgumentException("no argument");

        try {
            Long key = Long.parseLong(keyString);

            if (!collectionManager.removeElement(key)) {
                throw new IncorrectArgumentException("no elements with that key found");
            }

            System.out.printf("Element with key %d has been removed%n", key);
        } catch (NumberFormatException e) {
            throw new IncorrectArgumentException(keyString + " is not a valid key");
        }
    }
}
