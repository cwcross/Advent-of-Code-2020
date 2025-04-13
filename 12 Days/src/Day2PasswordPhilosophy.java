import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class Day2PasswordPhilosophy {
    public static void main(String[] args) {
        String[] input = readFile("12 Days/Files/Day2.txt");
        int validPasswords = 0;
        int newValidPasswords = 0;

        for (String s : input) {
            if (checkPasswordConditions(findPasswordConditions(s))) validPasswords++;
            if (checkNewPasswordConditions(findPasswordConditions(s))) newValidPasswords++;
        }

        System.out.println("Valid passwords: " + validPasswords);
        System.out.println("New valid passwords: " + newValidPasswords);
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

    public static boolean checkPasswordConditions(ArrayList<String> conditions) {
        int min = Integer.parseInt(conditions.get(0));
        int max = Integer.parseInt(conditions.get(1));
        char character = conditions.get(2).charAt(0);
        String password = conditions.get(3);
        int count = 0;
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) == character) count++;
        }
        return count >= min && count <= max;
    }

    public static boolean checkNewPasswordConditions(ArrayList<String> conditions) {
        int pos1 = Integer.parseInt(conditions.get(0));
        int pos2 = Integer.parseInt(conditions.get(1));
        char character = conditions.get(2).charAt(0);
        String password = conditions.get(3);

        if (password.charAt(pos1-1) == character && password.charAt(pos2-1) != character) return true;
        else return password.charAt(pos1 - 1) != character && password.charAt(pos2 - 1) == character;
    }

}
