package lab5.commands;

import lab5.CollectionManager;
import lab5.InputReader;
import lab5.exceptions.CancelCommandException;
import lab5.ticket.Person;
import lab5.ticket.Ticket;
import lab5.ticket.TicketReader;

import java.util.Map;

public class RemoveAnyByPerson extends Command {
    private final CollectionManager collectionManager;
    private final InputReader inputReader;
    private final TicketReader ticketReader;

    public RemoveAnyByPerson(CollectionManager collectionManager, InputReader inputReader, TicketReader ticketReader) {
        super("remove_any_by_person", "remove one element with specified person");
        this.collectionManager = collectionManager;
        this.inputReader = inputReader;
        this.ticketReader = ticketReader;
    }

    @Override
    public void execute(String argument) throws CancelCommandException {
        Person person = ticketReader.readPerson(inputReader);

        for (Map.Entry<Long, Ticket> entry : collectionManager.getEntrySet()) {
            if (!(entry.getValue().getPerson().equals(person))) continue;

            collectionManager.removeElement(entry.getKey());
            System.out.println("One element with that person has been removed");

            return;
        }

        System.out.println("There are no elements with specified person");
    }
}
