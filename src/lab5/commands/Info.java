package lab5.commands;

import lab5.CollectionManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.Date;

/**
 * Basic command.
 * Displays creation and last modified time of the collection's file, the collection type and the number of elements.
 *
 * @see lab5.commands.Command
 * @see lab5.CommandManager
 * @see CollectionManager
 */

public class Info extends Command {
    private final CollectionManager collectionManager;
    private final static String infoPattern = "Info about this collection:\nType: LinkedHashMap\nCreation date: %s\nLast modified date: %s\nNumber of elements: %d\n";

    public Info(CollectionManager collectionManager) {
        super("info", "display information about this collection");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument) {
        Path filePath = collectionManager.getFilePath();
        String createTime = null;
        String updateTime = null;

        try {
            createTime = new Date(((FileTime) Files.getAttribute(filePath, "creationTime")).toMillis()).toString();
            updateTime = new Date(Files.getLastModifiedTime(filePath).toMillis()).toString();
        } catch (IOException | NullPointerException ignored) {}

        System.out.printf((infoPattern), createTime, updateTime, collectionManager.getSize());
    }
}
