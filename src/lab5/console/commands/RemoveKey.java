package lab5.console.commands;

import lab5.CollectionManager;
import lab5.exceptions.IncorrectArgumentException;

public class RemoveKey extends Command {
    protected RemoveKey() {
        super("remove_key", "remove_key description", "remove_key key");
    }

    @Override
    protected void execute(String argument) throws IncorrectArgumentException {
        if (argument == null) throw new IncorrectArgumentException("no argument");

        try {
            Integer key = Integer.parseInt(argument);

            if (!CollectionManager.removeElement(key)) {
                throw new IncorrectArgumentException("no elements with that key found");
            }
            System.out.printf("Element with key %d has been removed%n", key);
        } catch (NumberFormatException e) {
            throw new IncorrectArgumentException(argument + " is not a valid key");
        }
    }
}
