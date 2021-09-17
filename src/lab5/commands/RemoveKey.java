package lab5.commands;

import lab5.CollectionManager;
import lab5.exceptions.IncorrectArgumentException;

public class RemoveKey extends Command {
    public RemoveKey() {
        super("remove_key", "remove element with specified key", "remove_key key");
    }

    @Override
    public void execute(String argument) throws IncorrectArgumentException {
        if (argument == null) throw new IncorrectArgumentException("no argument");

        try {
            Long key = Long.parseLong(argument);

            if (!CollectionManager.removeElement(key)) {
                throw new IncorrectArgumentException("no elements with that key found");
            }

            System.out.printf("Element with key %d has been removed%n", key);
        } catch (NumberFormatException e) {
            throw new IncorrectArgumentException(argument + " is not a valid key");
        }
    }
}
