package lab5;

import lab5.console.commands.CommandManager;
import lab5.console.ConsoleManager;
import lab5.exceptions.IncorrectFileException;
import lab5.ticket.Ticket;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// C:\Users\admin\IdeaProjects\Lab5\input.txt
public class Main {
    public static void main(String[] args) {
        try {
            ArrayList<String> ticketsFields = FileManager.readFile(args.length == 0 ? "input.txt" : args[0]);
            CollectionManager.initialize(ticketsFields);
        } catch (FileNotFoundException | IncorrectFileException e) {
            System.out.println(e.getMessage());
            return;
        }

        CommandManager.setCommands();
        ConsoleManager.run();
    }
}