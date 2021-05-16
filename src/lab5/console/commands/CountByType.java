package lab5.console.commands;

import lab5.CollectionManager;
import lab5.exceptions.UnreadableInputException;
import lab5.ticket.TicketType;

public class CountByType extends ComplexCommand {
    protected CountByType() {
        super("count_by_type", "count_by_type description", "count_by_type type");
    }

    @Override
    protected void execute(String argument) throws UnreadableInputException {
        try {
            TicketType ticketType = TicketType.valueOf(argument.toUpperCase());
            int countByType = CollectionManager.getKeySet(n -> n.getValue().getType().equals(ticketType)).size();

            System.out.println(countByType == 1
                    ? "1 element of this type was found"
                    : countByType + " elements of this type were found");
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new UnreadableInputException(argument + " is not a valid type");
        }
    }
}
