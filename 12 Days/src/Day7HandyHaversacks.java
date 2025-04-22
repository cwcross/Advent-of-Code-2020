import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// THIS ONE DOESNT COUNT!

public class Day7HandyHaversacks {
    public static void main(String[] args) throws IOException {
        String[] regulations = readFile("12 Days/Files/Day7.txt");
        System.out.println(findOuterBags(regulations, "Shiny Gold"));
    }

    public static String[] readFile(String filePath)  {
        try {
            return Files.readAllLines(Path.of(filePath)).toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    public static int findOuterBags(String[] regulations, String color) {
        int count = 0;
        for (String rule : regulations) {
            if (rule.contains(color.toLowerCase()) && !rule.strip().toLowerCase().substring(0,color.length()).equals(color.strip().toLowerCase())) {
                count++;
            }
        }
        return count;
    }

// I can't figure this one out. Going to jump around a little.
}
