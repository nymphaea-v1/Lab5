package lab5.console.commands;

import lab5.console.InputManager;

class Exit extends Command {
    protected Exit() {
        super("exit", "exit description");
    }

    @Override
    protected void execute(String argument) {
        InputManager.stop();
    }
}
