package lab5.console.commands;

import lab5.CollectionManager;
import lab5.exceptions.IncorrectInputException;

import java.util.HashSet;
import java.util.Set;

public class RemoveLowerKey extends ComplexCommand {
    protected RemoveLowerKey() {
        super("remove_lower_key", "remove_lower_key description", "remove_lower_key key");
    }

    @Override
    protected void execute(String argument) throws IncorrectInputException {
        try {
            Integer key = Integer.parseInt(argument);
            int sizeBefore = CollectionManager.getSize();

            CollectionManager.getEntrySet().removeIf(n -> n.getKey() < key);

            System.out.println("Elements removed: " + (sizeBefore - CollectionManager.getSize()));
        } catch (NumberFormatException | NullPointerException e) {
            throw new IncorrectInputException(argument + " is not a valid number");
        }
    }
}
