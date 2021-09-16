package lab5.commands;

import lab5.CollectionManager;

import java.io.IOException;

public class Save extends Command {
    public Save() {
        super("save", "save collection in the same file");
    }

    @Override
    public void execute(String argument) {
        try {
            CollectionManager.save();
            System.out.println("Collection has been saved in the file: " + CollectionManager.getFilePath());
        } catch (IOException e) {
            System.out.println("Failed to write a file: " + e.getMessage());
        }
    }
}
