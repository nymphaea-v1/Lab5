package lab5.console.commands;

import lab5.CollectionManager;
import lab5.console.InputManager;

import java.util.Date;

class Info extends Command {
    protected Info() {
        super("info", "info command description");
    }

    @Override
    protected void execute(String argument) {
        System.out.println("Info about this collection:\n" +
                "Type: LinkedHashMap\n" +
                "Creation date: " + new Date(CollectionManager.getCreateTimeStamp()) + "\n" +
                "Last modified date: " + new Date(CollectionManager.getUpdateTimeStamp()) + "\n" +
                "Number of elements: " + CollectionManager.getSize());
        if (InputManager.isConsoleInput()) System.out.println("Type show to see all of the elements");
    }
}
