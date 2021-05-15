package lab5;

import lab5.console.ConsoleManager;
import lab5.console.commands.CommandManager;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            String filePath = args.length == 0 ? "input.txt" : args[0];
            CollectionManager.initialize(filePath);
        } catch (IOException e) {
            System.out.printf("Cannot find specified file (%s)%n", e.getMessage());
            return;
        }

        CommandManager.setCommands();
        ConsoleManager.run();

//        String n = "CHEAP1 , []";
//        System.out.println(Arrays.asList(Arrays.toString(TicketType.values()).replaceAll("^.|.$", "").split(", ")).contains(n));
//        System.out.println(Arrays.toString(TicketType.values()).contains(n) && n.matches("[^(\\W|\\d)]+"));
    }

}