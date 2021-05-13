package lab5;

import lab5.exceptions.IncorrectFileException;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class FileManager {
    public static ArrayList<String> readFile(String filepath) throws FileNotFoundException, IncorrectFileException {
        ArrayList<String> result = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filepath));
        if (!scanner.hasNext()) throw new IncorrectFileException("file is empty");
        while (scanner.hasNext()) result.add(scanner.nextLine().replaceAll(" *, *", ","));

        System.out.println("Read file: " + filepath);
        return result;
    }

    public static void writeNewFile(String filepath, ArrayList<String> lines) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(filepath);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        int n = lines.size();

        for (int i = 0; i < n-1; i++) outputStreamWriter.write(lines.get(i) + "\n");
        outputStreamWriter.write(lines.get(n-1));
        outputStreamWriter.close();
    }


}
