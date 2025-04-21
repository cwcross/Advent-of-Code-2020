import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class AdventOfCode {
    public static String[] readFile(String filePath)  {
        try {
            return Files.readAllLines(Path.of(filePath)).toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return new String[0];
        }
    }
}
