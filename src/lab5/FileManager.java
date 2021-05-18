package lab5;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    public static ArrayList<String> readFile(String filepath) throws FileNotFoundException {
        ArrayList<String> result = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filepath));

        while (scanner.hasNext()) result.add(scanner.nextLine().trim().replaceAll(" *, *", ","));
        
        return result;
    }

    public static void writeNewFile(String filepath, String content) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(filepath);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);

        outputStreamWriter.write(content);
        outputStreamWriter.close();
    }

    public static long[] getFileTimeStamps(String filePath) throws IOException {
        Path file = Paths.get(filePath);
        BasicFileAttributes attributes = Files.readAttributes(file, BasicFileAttributes.class);

        long[] result = new long[2];

        result[0] = attributes.creationTime().toMillis();
        result[1] = attributes.lastModifiedTime().toMillis();

        return result;
    }
}
