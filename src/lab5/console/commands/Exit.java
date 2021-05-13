package lab5.console.commands;

import lab5.console.ConsoleManager;

class Exit extends SimpleCommand {
    protected Exit() {
        super("exit", "exit description");
    }

    @Override
    protected void execute() {
        ConsoleManager.stop();
    }
}
