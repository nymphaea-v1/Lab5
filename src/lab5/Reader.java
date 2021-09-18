package lab5;

import lab5.exceptions.IncorrectFieldException;

import java.util.Scanner;

public class Reader {
    public final String name;
    public final Readable reader;

    public Reader(String name, Readable reader) {
        this.name = name;
        this.reader = reader;
    }

    public interface Readable {
        Object read(Scanner scanner) throws IncorrectFieldException;
    }
}
