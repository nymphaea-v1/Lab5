package lab5.commands;

import lab5.InputReader;

public class Exit extends Command {
    private final InputReader inputReader;

    public Exit(InputReader inputReader) {
        super("exit", "exit the program (without saving)");
        this.inputReader = inputReader;
    }

    @Override
    public void execute(String argument) {
        inputReader.stopReading();
    }
}
