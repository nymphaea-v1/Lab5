package lab5.console.commands;

import lab5.CollectionManager;

import java.io.IOException;

public class Save extends Command {
    protected Save() {
        super("save", "save description");
    }

    @Override
    protected void execute(String argument) {
        try {
            CollectionManager.save();
            System.out.println("Collection has been saved in the file: " + CollectionManager.getFilePath());
        } catch (IOException e) {
            System.out.println("Failed to write a file");
        }
    }
}
