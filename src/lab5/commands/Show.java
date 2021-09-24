package lab5.commands;

import lab5.CollectionManager;

public class Show extends Command {
    private final CollectionManager collectionManager;

    public Show(CollectionManager collectionManager) {
        super("show", "display this collection");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument) {
        int size = collectionManager.getSize();

        if (size == 0) {
            System.out.println("Collection is empty");
            return;
        }

        System.out.println("All elements of the collection:");
        collectionManager.print();
        System.out.println("A total of " + size + " elements");
    }
}
