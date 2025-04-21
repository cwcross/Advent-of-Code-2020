import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Day4PassportProcessing {
    public static void main(String[] args) {
        ArrayList<String[]> passportData = readFile("12 Days/Files/Day4.txt");
        assert passportData != null;
        System.out.println("Total passports: " + passportData.size());
        System.out.println(findCorrectPassports(passportData));
    }

    public static ArrayList<String[]> readFile(String fileName) {
        try {
            ArrayList<String> lines = new ArrayList<>(Files.readAllLines(Path.of(fileName)));
            ArrayList<String[]> passportData = new ArrayList<>();
            ArrayList<String> currentPassport = new ArrayList<>();

            for (String line : lines) {
                if (line.trim().isEmpty()) {
                    passportData.add(currentPassport.toArray(new String[0]));
                    currentPassport.clear();
                } else {
                    String[] fields = line.split(" ");
                    for (String field : fields) {
                        currentPassport.add(field);
                    }
                }
            }

            if (!currentPassport.isEmpty()) {
                passportData.add(currentPassport.toArray(new String[0]));
            }

            return passportData;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String[] readFileHelper(String[] lines, int i, ArrayList<String> passport) {
        for (i = i; i < lines.length; i++) {
            String line = lines[i];
            String[] passportLine = line.split(" ");
            for (String entry: passportLine) {
                passport.add(entry);
                if (line.isEmpty()) {
                    return passport.toArray(new String[0]);
                }
            }
        }
        return passport.toArray(new String[0]);
    }

    public static int findCorrectPassports(ArrayList<String[]> passports) {
        int correctPassports = 0;
        for (String[] passport : passports) {
            if (passport.length == 8) {
                correctPassports++;
            } else if (passport.length == 7 && !containsCid(passport)) {
                correctPassports++;
            }
        }
        return correctPassports;
    }

    private static boolean containsCid(String[] passport) {
        for (String field : passport) {
            if (field.startsWith("cid:")) {
                return true;
            }
        }
        return false;
    }
}
