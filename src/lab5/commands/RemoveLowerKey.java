package lab5.commands;

import lab5.CollectionManager;
import lab5.exceptions.IncorrectArgumentException;

public class RemoveLowerKey extends Command {
    public RemoveLowerKey() {
        super("remove_lower_key", "remove all elements with a key lower than specified", "remove_lower_key key");
    }

    @Override
    public void execute(String keyString, CollectionManager collectionManager) throws IncorrectArgumentException {
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
