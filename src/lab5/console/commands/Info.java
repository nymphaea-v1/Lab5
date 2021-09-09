package lab5.console.commands;

import lab5.CollectionManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.Date;

class Info extends Command {
    protected Info() {
        super("info", "info command description");
    }

    @Override
    protected void execute(String argument) {
        Path filePath = CollectionManager.getFilePath();
        Date createTime = null;
        Date updateTime = null;

        try {
            createTime = new Date(((FileTime) Files.getAttribute(filePath, "creationTime")).toMillis());
            updateTime = new Date(Files.getLastModifiedTime(filePath).toMillis());
        } catch (IOException |NullPointerException ignored) {}

        System.out.println("Info about this collection:\n" +
                "Type: LinkedHashMap\n" +
                "Creation date: " + createTime + "\n" +
                "Last modified date: " + updateTime + "\n" +
                "Number of elements: " + CollectionManager.getSize());
    }
}
