package lab5.commands;

import lab5.CollectionManager;
import lab5.InputReader;
import lab5.ticket.Person;
import lab5.ticket.Ticket;
import lab5.ticket.TicketReader;

import java.util.Map;

/**
 * Complex command.
 * Removes all elements whose person is lower than the given one.
 *
 * @see lab5.commands.Command
 * @see lab5.CommandManager
 * @see CollectionManager
 * @see InputReader
 */

public class RemoveAnyByPerson extends Command {
    private final CollectionManager collectionManager;
    private final InputReader inputReader;

    public RemoveAnyByPerson(CollectionManager collectionManager, InputReader inputReader) {
        super("remove_any_by_person", "remove one element with specified person");
        this.collectionManager = collectionManager;
        this.inputReader = inputReader;
    }

    @Override
    public void execute(String argument) {
        Person person = TicketReader.readPerson(inputReader);

        for (Map.Entry<Long, Ticket> entry : collectionManager.getEntrySet()) {
            if (!(entry.getValue().getPerson().equals(person))) continue;

            collectionManager.removeElement(entry.getKey());
            System.out.println("One element with that person has been removed");

            return;
        }

        System.out.println("There are no elements with specified person");
    }
}
