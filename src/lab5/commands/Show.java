package lab5.commands;

import lab5.CollectionManager;

public class Show extends Command {
    public Show() {
        super("show", "show description");
    }

    @Override
    public void execute(String argument) {
        int size = CollectionManager.getSize();

        if (size == 0) {
            System.out.println("Collection is empty");
            return;
        }

        System.out.println("All elements of the collection:");
        CollectionManager.print();
        System.out.println("A total of " + size + " elements");
    }
}
