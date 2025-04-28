import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Day10AdapterArray {
    public static void main(String[] args) {
        int[] values = readFile("12 Days/Files/Day10.txt");
        System.out.println(Arrays.toString(values));
        System.out.println(values.length);
        System.out.println(findDistribution(values));
        System.out.println(countIterations(values));
    }

    public static int[] readFile(String filePath)  {
        try {
            String[] lines = Files.readAllLines(Path.of(filePath)).toArray(new String[0]);
            int[] values = new int[lines.length + 2];
            for (int i = 0, linesLength = lines.length; i <= linesLength; i++) {
                if (i == linesLength) {
                    values[i+1] = 9999999;
                }
                else {
                    String line = lines[i];
                    values[i+1] = Integer.parseInt(line);
                }
            }
            Arrays.sort(values);
            values[values.length-1] = values[values.length-2] + 3;
            return values;
        } catch (IOException e) {
            e.printStackTrace();
            return new int[0];
        }
    }

    public static int findDistribution(int[] values) {
        int ones = 0;
        int twos = 0;
        int threes = 0;
        for (int i = 1; i < values.length; i++) {
            switch (values[i] - values[i-1]) {
                case 1:
                    ones++;
                    break;
                case 2:
                    twos++;
                    break;
                case 3:
                    threes++;
                    break;
            }
        }
        System.out.println("Ones: " + ones);
        System.out.println("Twos: " + twos);
        System.out.println("Threes: " + threes);
        return ones * threes;
    }

//    public static long countIterations(int[] values) {
//        long count = 0;
//        long hits = 0;
//        for (int i = 1; i < values.length-1; i++) {
//            switch (values[i] - values[i-1]) {
//                case 1:
//                    if (values[i+1] - values[i] <= 2) {
//                        count += (long) Math.pow(2, hits);
//                        hits++;
//                    }
//                    break;
//                case 2:
//                    if (values[i+1] - values[i] <= 1) {
//                        count += (long) Math.pow(2, hits);
//                        hits++;
//                    }
//                    break;
//                case 3:
//                    continue;
//            }
//        }
//        return count;
//    }

    public static long countIterations(int[] vals) {
        int n = vals.length;
        long[] ways = new long[n];
        ways[0] = 1;

        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0 && vals[i] - vals[j] <= 3; j--) {
                ways[i] += ways[j];
            }
        }

        return ways[n - 1];
    }


}
