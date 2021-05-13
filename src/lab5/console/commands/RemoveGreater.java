package lab5.console.commands;

import lab5.CollectionManager;
import lab5.exceptions.IncorrectArgumentException;

import java.util.ArrayList;

public class RemoveGreater extends ComplexCommand {

    protected RemoveGreater() {
        super("remove_greater", "remove_greater description", "remove_greater {element}");
    }

    @Override
    void execute(String argument) throws IncorrectArgumentException {
        try {
            long key = Long.parseLong(argument);
            ArrayList<Long> keySetToRemove = CollectionManager.getGreaterOrLower(key, true);

            if (keySetToRemove == null) {
                System.out.println("There are no greater elements");
                return;
            }

            CollectionManager.removeElements(keySetToRemove);
            System.out.println(keySetToRemove.size() + " elements was removed");
        } catch (NumberFormatException | NullPointerException e) {
            throw new IncorrectArgumentException(argument + " is not a valid key");
        }
    }
}
