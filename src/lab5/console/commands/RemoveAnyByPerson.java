package lab5.console.commands;

import lab5.CollectionManager;
import lab5.exceptions.CancelCommandException;
import lab5.ticket.Person;
import lab5.ticket.TicketManager;

import java.util.HashSet;
import java.util.Set;

public class RemoveAnyByPerson extends SimpleCommand {
    protected RemoveAnyByPerson() {
        super("remove_any_by_person", "remove_any_by_person description");
    }

    @Override
    protected void execute() throws CancelCommandException {
        Person person = TicketManager.createPerson(TicketManager.readPersonFields());
        Set<Integer> keySet = new HashSet<>();

        CollectionManager.getEntrySet().stream()
                .filter(n -> n.getValue().getPerson().equals(person))
                .forEach(n -> keySet.add(n.getKey()));

        if (keySet.isEmpty()) System.out.println("There are no elements with that person");
        else {
            CollectionManager.removeElement(keySet.toArray(new Integer[0])[0]);
            System.out.println("One element with that person was removed");
        }
    }
}
