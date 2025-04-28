import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Day11SeatingSystem {
    public static void main(String[] args) {
        String[] seatingChart = readFile("12 Days/Files/Day11.txt");
        System.out.println(partOne(seatingChart));
        System.out.println(partTwo(seatingChart));
    }

    public static String[] readFile(String fileName) {
        try {
            return Files.readAllLines(Path.of(fileName)).toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    public static int partTwo(String[] seatingChart) {
        String[] seating = seatingChart;
        while (true) {
            String[] newSeating = seating.clone();
            newSeating = applyRules2(newSeating);
            if (Arrays.equals(newSeating, seating)) {
                seating = newSeating;
                break;
            }
            else {
                seating = newSeating;
            }
        }

        // Count the number of empty seats
        int emptySeats = 0;
        for (String s : seating) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '#') emptySeats++;
            }
        }
        return emptySeats;
    }

    public static int partOne(String[] seatingChart) {
        String[] seating = seatingChart;
        while (true) {
            String[] newSeating = seating.clone();
            newSeating = applyRules(newSeating);
            if (Arrays.equals(newSeating, seating)) {
                seating = newSeating;
                break;
            }
            else {
                seating = newSeating;
            }
        }

        // Count the number of empty seats
        int emptySeats = 0;
        for (String s : seating) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '#') emptySeats++;
            }
        }
        return emptySeats;
    }

    public static String[] applyRules(String[] oldSeating) {
        String[] newSeating = new String[oldSeating.length];
        for (int i = 0; i < oldSeating.length; i++) {
            char[] chars = oldSeating[i].toCharArray();
            for (int j = 0; j < chars.length; j++) {
                chars[j] = seatStatus(oldSeating, i, j);
            }
            newSeating[i] = new String(chars);
        }
        return newSeating;
    }

    public static String[] applyRules2(String[] oldSeating) {
        String[] newSeating = new String[oldSeating.length];
        for (int i = 0; i < oldSeating.length; i++) {
            char[] chars = oldSeating[i].toCharArray();
            for (int j = 0; j < chars.length; j++) {
                chars[j] = seatStatus2(oldSeating, i, j);
            }
            newSeating[i] = new String(chars);
        }
        return newSeating;
    }

    private static char seatStatus(String[] seats, int row, int col) {
        // Base case of sorts
        if (seats[row].charAt(col) == '.') return '.';

        // Create a list of adjacent tiles
        ArrayList<Character> adjacentSeats = new ArrayList<>();
        if (col > 0) adjacentSeats.add(seats[row].charAt(col - 1));
        if (col+1 < seats[row].length()) adjacentSeats.add(seats[row].charAt(col + 1));
        if (row > 0) adjacentSeats.add(seats[row - 1].charAt(col));
        if (row+1 < seats.length) adjacentSeats.add(seats[row + 1].charAt(col));
        if (col > 0 && row > 0) adjacentSeats.add(seats[row - 1].charAt(col - 1));
        if (col > 0 && row+1 < seats.length) adjacentSeats.add(seats[row + 1].charAt(col - 1));
        if (col+1 < seats[row].length() && row+1 < seats.length) adjacentSeats.add(seats[row + 1].charAt(col + 1));
        if (col+1 < seats[row].length() && row > 0) adjacentSeats.add(seats[row - 1].charAt(col + 1));

        // Logic for empty seats
        if (seats[row].charAt(col) == 'L') {
            if (!adjacentSeats.contains('#')) return '#';
            else return 'L';
        }

        // Logic for full seats
        if (seats[row].charAt(col) == '#') {
            if (Collections.frequency(adjacentSeats, '#') >= 4) return 'L';
            else return '#';
        }

        // Another base case of sorts
        else return seats[row].charAt(col);
    }

    private static char seatStatus2(String[] seats, int row, int col) {
        // Base Case
        if (seats[row].charAt(col) == '.') return '.';

        // Create a list of viewable tiles in 8 directions
        ArrayList<Character> viewableSeats = new ArrayList<>();

        // Look up
        for (int r = row-1; r >= 0; r--) {
            char ch = seats[r].charAt(col);
            viewableSeats.add(ch);
            if (ch != '.') break;
        }

        // Look down
        for (int r = row+1; r < seats.length; r++) {
            char ch = seats[r].charAt(col);
            viewableSeats.add(ch);
            if (ch != '.') break;
        }

        // Look left
        for (int c = col-1; c >= 0; c--) {
            char ch = seats[row].charAt(c);
            viewableSeats.add(ch);
            if (ch != '.') break;
        }

        // Look right
        for (int c = col+1; c < seats[row].length(); c++) {
            char ch = seats[row].charAt(c);
            viewableSeats.add(ch);
            if (ch != '.') break;
        }

        // Look up-left
        for (int r = row-1, c = col-1; r >= 0 && c >= 0; r--, c--) {
            char ch = seats[r].charAt(c);
            viewableSeats.add(ch);
            if (ch != '.') break;
        }

        // Look down-right
        for (int r = row+1, c = col+1; r < seats.length && c < seats[r].length(); r++, c++) {
            char ch = seats[r].charAt(c);
            viewableSeats.add(ch);
            if (ch != '.') break;
        }

        // Look up-right
        for (int r = row-1, c = col+1; r >= 0 && c < seats[r].length(); r--, c++) {
            char ch = seats[r].charAt(c);
            viewableSeats.add(ch);
            if (ch != '.') break;
        }

        // look down-left
        for (int r = row+1, c = col-1; r < seats.length && c >= 0; r++, c--) {
            char ch = seats[r].charAt(c);
            viewableSeats.add(ch);
            if (ch != '.') break;
        }

        // Logic for empty seats
        if (seats[row].charAt(col) == 'L') {
            if (!viewableSeats.contains('#')) return '#';
            else return 'L';
        }

        // Logic for full seats
        if (seats[row].charAt(col) == '#') {
            if (Collections.frequency(viewableSeats, '#') >= 5) return 'L';
            else return '#';
        }

        // Another base case of sorts
        else return seats[row].charAt(col);
    }


}
