package lab5.console.commands;

import lab5.CollectionManager;

class Info extends SimpleCommand {
    protected Info() {
        super("info", "info command description");
    }

    @Override
    protected void execute() {
        System.out.println("Info about this collection:\n" +
                "Type: LinkedHashMap,\n" +
                "Initialization time: " + CollectionManager.getInitDate() + ",\n" +
                "Number of elements: " + CollectionManager.getSize() + ".\n" +
                "Type show to see all of the elements");
    }
}
