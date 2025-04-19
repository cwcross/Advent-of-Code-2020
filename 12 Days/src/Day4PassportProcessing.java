import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Day4PassportProcessing {
    public static void main(String[] args) {}

    public static ArrayList<String[]> readFile(String fileName) {
        try {
            String[] lines = Files.readAllLines(Path.of(fileName)).toArray(new String[0]);
            for (line : lines) {

            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int findCorrectPassports(ArrayList<String[]> passports) {
        int correctPassports = 0;
        for (String[] passport : passports) {
            if (passport.length == 8) {
                correctPassports++;
            }
        }
        return correctPassports;
    }
}
