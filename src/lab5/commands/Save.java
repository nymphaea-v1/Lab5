package lab5.commands;

import lab5.CollectionManager;

import java.io.IOException;

/**
 * Basic command.
 * Saves the entire collection into its file.
 *
 * @see lab5.commands.Command
 * @see lab5.CommandManager
 * @see CollectionManager
 */

public class Save extends Command {
    private final CollectionManager collectionManager;

    public Save(CollectionManager collectionManager) {
        super("save", "save collection in the same file");
        this.collectionManager = collectionManager;
    }

    @Override
        public void execute(String argument) {
        try {
            collectionManager.save();
            System.out.println("Collection has been saved in the file: " + collectionManager.getFilePath());
        } catch (IOException e) {
            System.out.println("Failed to write a file: " + e.getMessage());
        }
    }
}
