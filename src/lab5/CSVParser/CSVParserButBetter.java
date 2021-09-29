package lab5.CSVParser;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Scanner;

public class CSVParserButBetter implements Iterator<String> {
    private final Scanner scanner;
    public boolean lineSkip = false;

    public CSVParserButBetter(Path source) throws IOException {
        scanner = new Scanner(source).useDelimiter("");
    }

    @Override
    public boolean hasNext() {
        return scanner.hasNext();
    }

    @Override
    public String next() {
        StringBuilder resultBuilder = new StringBuilder();
        String next = scanner.skip("\\s*").next();

        // check if the element is not surrounded by quotation marks
        // if so, read an entire element until the end (comma or line separator) and return it
        if (!next.equals("\"")) {
            while (!next.matches("[,\n]")) {
                if (next.equals("\"")) throw new CSVParsingException(("quotation mark inside an unquoted element"));
                resultBuilder.append(next);
                next = scanner.next();
            }

            lineSkip = next.matches("\n");
            return resultBuilder.toString().trim();
        }

        // skip the first quotation mark
        next = scanner.next();

        while (true) {
            // check if the next symbol is not a quotation mark
            // if so, add it to result and go to the next symbol
            if (!next.equals("\"")) {
                resultBuilder.append(next);
                next = scanner.next();
                continue;
            }

            // check if quotation mark is inside an element
            // if so, add it to result and go to the next symbol
            next = scanner.next();
            if (next.equals("\"")) {
                resultBuilder.append(next);
                next = scanner.next();
                continue;
            }

            // skip any left symbols to the end
            if (!next.matches("[,\n]")) {
                next = scanner.skip("\\s*").next();
                if (!next.matches("[,\n]")) throw new CSVParsingException("symbol after ending quotation mark");
            }

            lineSkip = next.matches("\n");
            return resultBuilder.toString().trim();
        }
    }

    public void skipLine() {
        scanner.nextLine();
        lineSkip = true;
    }

    public void close() {
        scanner.close();
    }

    public static class CSVParsingException extends RuntimeException {
        CSVParsingException(String message) {
            super(message);
        }
    }
}
