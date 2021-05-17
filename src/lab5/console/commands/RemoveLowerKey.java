package lab5.console.commands;

import lab5.CollectionManager;
import lab5.exceptions.UnreadableInputException;

import java.util.HashSet;
import java.util.Set;

public class RemoveLowerKey extends ComplexCommand {
    protected RemoveLowerKey() {
        super("remove_lower_key", "remove_lower_key description", "remove_lower_key key");
    }

    @Override
    protected void execute(String argument) throws UnreadableInputException {
        try {
            Integer key = Integer.parseInt(argument);
            Set<Integer> keySet = new HashSet<>();

            CollectionManager.getKeySet().stream()
                    .filter(n -> n < key)
                    .forEach(keySet::add);

            CollectionManager.removeElements(keySet);
            System.out.println(keySet.size() + " elements was removed");
        } catch (NumberFormatException | NullPointerException e) {
            throw new UnreadableInputException(argument + " is not a valid number");
        }
    }
}
