import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class Day2PasswordPhilosophy {
    public static void main(String[] args) {
        String[] input = readFile("Files/Day2.txt");
        
    }
    
    public static String[] readFile(String fileName) {
        try {
            return Files.readAllLines(Path.of(fileName)).toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    public static ArrayList<String> findPasswordConditions(String input) {
        String character;
        String password;
        String numbers = "";
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '-' || Character.isDigit(input.charAt(i))) {
                numbers += input.charAt(i);
            }
        }
        String[] range = numbers.split("-");
        String min = range[0];
        String max = range[1];

        for (int i = 0; true; i++) {
            if (Character.isAlphabetic(input.charAt(i))) {
                character = "" + input.charAt(i);
                password = findPassword(input, i+3);
                break;
            }
        }
        return new ArrayList<String>(java.util.Arrays.asList(min, max, character, password));
    }

    private static String findPassword(String input, int passwordStart) {
        String password;
        return password = input.substring(passwordStart);
    }


}
