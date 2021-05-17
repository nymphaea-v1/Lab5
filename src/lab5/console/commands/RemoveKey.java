package lab5.console.commands;

import lab5.CollectionManager;
import lab5.exceptions.IncorrectInputException;

public class RemoveKey extends ComplexCommand {
    protected RemoveKey() {
        super("remove_key", "remove_key description", "remove_key key");
    }

    @Override
    protected void execute(String argument) throws IncorrectInputException {
        try {
            Integer key = Integer.parseInt(argument);

            if (!CollectionManager.removeElement(key)) throw new IncorrectInputException("no elements with that key found");
            System.out.printf("Element with key %d has been removed%n", key);
        } catch (NumberFormatException | NullPointerException e) {
            throw new IncorrectInputException(argument + " is not a valid key");
        }
    }
}
