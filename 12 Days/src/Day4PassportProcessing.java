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
        System.out.println(findCorrectPassportsPart2(passportData));
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

    public static int findCorrectPassportsPart2(ArrayList<String[]> passports) {
        int correctPassports = 0;
        for (String[] passport : passports) {
            if (isValidPassport(passport)) {
                correctPassports++;
            }
        }
        return correctPassports;
    }

    private static boolean isValidPassport(String[] passport) {
        if (passport.length < 7) return false;
        if (passport.length == 7 && containsCid(passport)) return false;

        for (String field : passport) {
            String[] keyValue = field.split(":");
            if (!validateField(keyValue[0], keyValue[1])) {
                return false;
            }
        }

        return true;
    }


    private static boolean validateField(String key, String value) {
        try {
            switch (key) {
                case "byr":
                    int byr = Integer.parseInt(value);
                    return byr >= 1920 && byr <= 2002;

                case "iyr":
                    int iyr = Integer.parseInt(value);
                    return iyr >= 2010 && iyr <= 2020;

                case "eyr":
                    int eyr = Integer.parseInt(value);
                    return eyr >= 2020 && eyr <= 2030;

                case "hgt":
                    if (value.endsWith("cm")) {
                        int cm = Integer.parseInt(value.replace("cm", ""));
                        return cm >= 150 && cm <= 193;
                    } else if (value.endsWith("in")) {
                        int in = Integer.parseInt(value.replace("in", ""));
                        return in >= 59 && in <= 76;
                    } else {
                        return false;
                    }

                case "hcl":
                    return value.matches("^#[0-9a-f]{6}$");

                case "ecl":
                    return value.equals("amb") || value.equals("blu") || value.equals("brn") ||
                            value.equals("gry") || value.equals("grn") || value.equals("hzl") || value.equals("oth");

                case "pid":
                    return value.matches("^\\d{9}$");

                case "cid":
                    return true;

                default:
                    return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

}
