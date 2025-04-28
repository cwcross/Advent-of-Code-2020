import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class Day9EncodingError {
    public static void main(String[] args) {
        System.out.println(findError(readFile("12 Days/Files/Day9.txt")));
        Long[] values = readFile("12 Days/Files/Day9.txt");
        Long key = 41682220L;
        System.out.println(findWeakness(values, key));
    }

    public static Long[] readFile(String filePath)  {
        try {
            String[] lines = Files.readAllLines(Path.of(filePath)).toArray(new String[0]);
            Long[] values = new Long[lines.length];
            for (int i = 0, linesLength = lines.length; i < linesLength; i++) {
                String line = lines[i];
                values[i] = Long.parseLong(line);
            }
            return values;
        } catch (IOException e) {
            e.printStackTrace();
            return new Long[0];
        }
    }

    public static Long findError(Long[] values) {
        for (int i = 25; i < values.length; i++) {
            if (checkValid(values, i)) continue;
            return values[i];
        }
        return null;
    }

    private static Boolean checkValid(Long[] values, int i) {
        for (int j = i-25; j < i; j++) {
            Long value = values[i];
            for (int k = j; k < i; k++) {
                if (k == j) continue;
                if (values[k] == null) continue;
                if (!values[k].equals(values[j])) {
                    if (value == values[j] + values[k]) return true;
                }
            }
        }
        return false;
    }

    public static Long findWeakness(Long[] values, Long key) {
        for (int i = 0; i < values.length; i++) {
            Long smallest = values[i];
            Long largest = values[i];
            Long temp = values[i];
            for (int j = i+1; j < values.length; j++) {
                if (values[j] > largest) largest = values[j];
                if (values[j] < smallest) smallest = values[j];
                temp += values[j];
                if (temp.equals(key)) return smallest + largest;
                if (temp > key) break;
            }
        }
        return null;
    }
}

