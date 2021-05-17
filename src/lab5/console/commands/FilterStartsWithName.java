package lab5.console.commands;

import lab5.CollectionManager;
import lab5.exceptions.UnreadableInputException;
import lab5.ticket.Ticket;

import java.util.Set;
import java.util.stream.Collectors;

public class FilterStartsWithName extends ComplexCommand {
    protected FilterStartsWithName() {
        super("filter_starts_with_name", "filter_starts_with_name description", "filter_starts_with_name name");
    }

    @Override
    protected void execute(String argument) throws UnreadableInputException {
        try {
            Set<Ticket> valueSet = CollectionManager.getValueCollection().stream()
                    .filter(n -> n.getName().startsWith(argument))
                    .collect(Collectors.toSet());
            int valueSetSize = valueSet.size();

            if (valueSetSize == 0) System.out.printf("There are no elements whose name starts with \"%s\"\n", argument);
            else {
                System.out.println("Elements found: " + valueSetSize);
                valueSet.forEach(System.out::println);
            }
        } catch (NullPointerException e) {
            throw new UnreadableInputException("empty");
        }
    }
}
