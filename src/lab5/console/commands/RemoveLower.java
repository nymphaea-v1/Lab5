package lab5.console.commands;

import lab5.CollectionManager;
import lab5.exceptions.IncorrectArgumentException;

import java.util.ArrayList;

public class RemoveLower extends ComplexCommand {
    protected RemoveLower() {
        super("remove_lower", "remove_lower description", "remove_lower {element}");
    }

    @Override
    void execute(String argument) throws IncorrectArgumentException {
        try {
            long key = Long.parseLong(argument);
            ArrayList<Long> keySetToRemove = CollectionManager.getGreaterOrLower(key, false);

            if (keySetToRemove.isEmpty()) {
                System.out.println("There are no lower elements");
                return;
            }

            CollectionManager.removeElements(keySetToRemove);
            System.out.println(keySetToRemove.size() + " elements was removed");
        } catch (NumberFormatException | NullPointerException e) {
            throw new IncorrectArgumentException(argument + " is not a valid key");
        }
    }
}
