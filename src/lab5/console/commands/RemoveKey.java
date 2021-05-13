package lab5.console.commands;

import lab5.CollectionManager;
import lab5.exceptions.IncorrectArgumentException;

public class RemoveKey extends ComplexCommand {
    protected RemoveKey() {
        super("remove_key", "remove_key description", "remove_key {key}");
    }

    @Override
    protected void execute(String argument) throws IncorrectArgumentException {
        try {
            Long key = Long.parseLong(argument);

            if (!CollectionManager.removeElement(key)) {
                throw new IncorrectArgumentException("no elements with that key found");
            }

            System.out.println("Element with key " + argument + " was removed");
        } catch (NumberFormatException | NullPointerException e) {
            throw new IncorrectArgumentException(argument + " is not a valid key");
        }
    }
}
