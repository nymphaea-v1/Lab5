package lab5.console.commands;

import lab5.CollectionManager;
import lab5.exceptions.IncorrectArgumentException;

public class RemoveLowerKey extends Command {
    protected RemoveLowerKey() {
        super("remove_lower_key", "remove_lower_key description", "remove_lower_key key");
    }

    @Override
    protected void execute(String argument) throws IncorrectArgumentException {
        if (argument == null) throw new IncorrectArgumentException("no argument");

        try {
            Integer key = Integer.parseInt(argument);
            int sizeBefore = CollectionManager.getSize();

            CollectionManager.getEntrySet().removeIf(n -> n.getKey() < key);

            System.out.println("Elements removed: " + (sizeBefore - CollectionManager.getSize()));
        } catch (NumberFormatException e) {
            throw new IncorrectArgumentException(argument + " is not a valid number");
        }
    }
}
