package lab5.commands;

import lab5.InputReader;

public class Exit extends Command {
    public Exit() {
        super("exit", "exit description");
    }

    @Override
    public void execute(String argument) {
        InputReader.stopReading();
    }
}
